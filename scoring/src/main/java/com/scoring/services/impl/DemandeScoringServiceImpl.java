package com.scoring.services.impl;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.services.IEntrepriseService;
import com.scoring.utils.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.services.IDemandeScoring;

import java.util.List;


@Service
public class DemandeScoringServiceImpl implements IDemandeScoring {

	@Autowired
	private DemandeScoringRepository demandeScoringRepository;
	
	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Value("${app.administration.url}")
    private String appUrl;

	@Autowired
	private IEntrepriseService entrepriseService;


    @Override
    public List<DemandeScoringDTO> getDemandes() throws DemandeException {
    	List<DemandeScoring> demandes = demandeScoringRepository.findAllByOrderByIdDesc();
        return dtoFactory.createListDemandeScoring(demandes);
    }

	@Override
	public DemandeScoringDTO getDemande(Long id) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() ->  new DemandeException("Demande ::"+id+" not found."));
		return dtoFactory.createDemandeScoring(demande);
	}

	@Override
	public DemandeScoringDTO createDemande(Long idEntreprise) throws DemandeException {
		try {
			EntrepriseDTO entreprise = entrepriseService.getEntreprise(idEntreprise);
			DemandeScoringDTO demande = new DemandeScoringDTO();
			demande.setEntrepriseDTO(entreprise);
			demande.setStatus(Constante.ETAT_DEMANDE_BROUILLON);

			DemandeScoring model = modelFactory.createDemandeScoring(demande);
			demandeScoringRepository.save(model);
			demande.setId(model.getId());
			return demande;
		} catch (EntrepriseException e) {
			throw new DemandeException(e.getMessage(), e);
		}
	}
}
