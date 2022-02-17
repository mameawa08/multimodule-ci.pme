package com.scoring.services.impl;

import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.DemandeAccompagnementDTO;
import com.scoring.exceptions.DemandeAccompagnementException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.AccompagnementAEligibilte;
import com.scoring.models.DemandeAccompagnement;
import com.scoring.models.DemandeScoring;
import com.scoring.repository.AccompagnementAEligibiliteRepository;
import com.scoring.repository.DemandeAccompagnementRepository;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.services.IDemandeAccompagnementService;
import com.scoring.services.IUserService;
import com.scoring.utils.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DemandeAccompagnementServiceImpl implements IDemandeAccompagnementService {

    @Autowired
    private DemandeAccompagnementRepository demandeAccompagnementRepository;

    @Autowired
    private DemandeScoringRepository demandeScoringRepository;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private ModelFactory modelFactory;

    @Autowired
    private AccompagnementAEligibiliteRepository accompagnementAEligibiliteRepository;

    @Autowired
    private IUserService userService;

    @Override
    public DemandeAccompagnementDTO getDemandeAccompagnementByDemandeScoring(Long demandeScoring) {
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findByDemandeScoring_Id(demandeScoring);
        return dtoFactory.createDemandeAccompagnement(demandeAccompagnement);
    }

    @Override
    public List<DemandeAccompagnementDTO> getDemandeAccompagnementByEntreprise(Long entreprise) {
        List<DemandeAccompagnement> demandeAccompagnement = demandeAccompagnementRepository.findByDemandeScoring_Entreprise_Id(entreprise);
        return dtoFactory.createListDemandeAccompagnement(demandeAccompagnement);
    }

    @Override
    public DemandeAccompagnementDTO createDemandeAccompagnement(Long idDemandeScoring) throws DemandeAccompagnementException {
        DemandeScoring demandeScoring = demandeScoringRepository.findById(idDemandeScoring).orElseThrow(() -> new DemandeAccompagnementException("Demande scoring not found."));
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findByDemandeScoring_Id(demandeScoring.getId());
        DemandeAccompagnementDTO dto;
        if (demandeAccompagnement != null) {
            throw new DemandeAccompagnementException("Une demande d'accompagnement existe déjà.");
        } else {
            dto = new DemandeAccompagnementDTO();
            dto.setDateCreation(new Date());
            dto.setDemandeScoring(dtoFactory.createDemandeScoring(demandeScoring));
            dto.setStatus(Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_BROUILLON);

            demandeAccompagnement = modelFactory.createDemandeAccompagnement(dto);

            demandeAccompagnementRepository.saveAndFlush(demandeAccompagnement);
            dto.setId(demandeAccompagnement.getId());

        }
        return dto;
    }

    @Override
    public DemandeAccompagnementDTO envoyerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException {
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findById(idDemandeAccompagnement).orElseThrow(() -> new DemandeAccompagnementException("Demande accompagnement not found"));
        if (demandeAccompagnement.getStatus() == Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_BROUILLON || demandeAccompagnement.getStatus() == Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_ANNULEE) {
            demandeAccompagnement.setStatus(Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_ENVOYEE);
            demandeAccompagnement.setDateEnvoi(new Date());
            demandeAccompagnementRepository.saveAndFlush(demandeAccompagnement);
        }
        return dtoFactory.createDemandeAccompagnement(demandeAccompagnement);
    }

    @Override
    public DemandeAccompagnementDTO receptionnerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException {
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findById(idDemandeAccompagnement).orElseThrow(() -> new DemandeAccompagnementException("Demande accompagnement not found"));
        AccessTokenDetails user = userService.getConnectedUser();
        if (demandeAccompagnement.getStatus() == Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_ENVOYEE) {
            demandeAccompagnement.setStatus(Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_RECEPTIONNEE);
            demandeAccompagnement.setDateReception(new Date());
            demandeAccompagnement.setTraiterPar(user.getUserId());
            demandeAccompagnementRepository.saveAndFlush(demandeAccompagnement);
        }
        return dtoFactory.createDemandeAccompagnement(demandeAccompagnement);
    }

    @Override
    public boolean annulerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException {
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findById(idDemandeAccompagnement).orElseThrow(() -> new DemandeAccompagnementException("Demande accompagnement not found"));
        List<AccompagnementAEligibilte> aEligibiltes = accompagnementAEligibiliteRepository.findByDemandeAccompagnement_Id(idDemandeAccompagnement);

        accompagnementAEligibiliteRepository.deleteAll(aEligibiltes);
        demandeAccompagnementRepository.delete(demandeAccompagnement);

        return true;
    }

    @Override
    public boolean closeDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException {
        DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findById(idDemandeAccompagnement).orElseThrow(() -> new DemandeAccompagnementException("Demande accompagnement not found"));
        if (demandeAccompagnement.getStatus() == Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_RECEPTIONNEE) {
            demandeAccompagnement.setStatus(Constante.STATUT_DEMANDE_ACCOMPAGNEMENT_CLOTUREE);
            demandeAccompagnement.setDateReception(new Date());
            demandeAccompagnementRepository.saveAndFlush(demandeAccompagnement);
        }

        return true;
    }


}
