package com.scoring.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.DemandeScoring;
import com.scoring.payloads.DemandePayload;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.services.IDemandeScoring;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IMailService;
import com.scoring.services.IUserService;
import com.scoring.utils.Constante;


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

	@Autowired
	private IUserService userService;

	@Autowired
	private IMailService mailService;


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

			DemandeScoring demandeScoring = demandeScoringRepository.findByEntreprise_IdAndStatusNot(idEntreprise, Constante.ETAT_DEMANDE_CLOTURE);
			if (demandeScoring != null){
				throw new DemandeException("Une demande de scoring est deja en cours.");
			}
			DemandeScoringDTO demande = new DemandeScoringDTO();
			demande.setEntrepriseDTO(entreprise);
			demande.setStatus(Constante.ETAT_DEMANDE_BROUILLON);
			demande.setDateCreation(new Date());

			DemandeScoring model = modelFactory.createDemandeScoring(demande);
			demandeScoringRepository.save(model);
			demande.setId(model.getId());
			return demande;
		} catch (EntrepriseException e) {
			throw new DemandeException(e.getMessage(), e);
		}
	}

	@Override
	public boolean envoyerDemande(Long id) throws DemandeException{
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DecodingException("Demande scoring :: "+id+" not found."));
        try {
            demande.setStatus(Constante.ETAT_DEMANDE_ENVOYE);
            demande.setDateEnvoie(new Date());
            demandeScoringRepository.save(demande);
            List<UserDTO> users = userService.getUsersByProfil(Constante.PROFIL_EXPERT_PME);
            for (UserDTO user: users) {
                mailService.sendDemandeNotification(user, demande.getEntreprise().getRaisonSociale());
            }
            return true;
        } catch (Exception e) {
            throw new DemandeException("Demande scoring :: "+e.getMessage(), e);
        }
    }

    @Override
    public boolean receptionnerDemande(Long id) throws DemandeException {
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
        try{
        	demande.setStatus(Constante.ETAT_DEMANDE_EN_COURS);
        	demande.setDateReception(new Date());
        	demandeScoringRepository.save(demande);
        	return true;
		}
        catch (Exception e){
        	throw new DemandeException(e.getMessage(), e);
		}
    }
    
    @Override
    public boolean rejeterDemande(Long id, DemandePayload demandePayload) throws DemandeException {
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
        try{
        	if(demande.getStatus()==Constante.ETAT_DEMANDE_EN_COURS)
        		demande.setStatus(Constante.ETAT_DEMANDE_REJETE);
        	demande.setMotif_rejet(demandePayload.getMotif_rejet());
        	demandeScoringRepository.save(demande);
        	return true;
		}
        catch (Exception e){
        	throw new DemandeException(e.getMessage(), e);
		}
    }
    
    @Override
    public boolean validerDemandeProvisoire(Long id) throws DemandeException {
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
        try{
        	if(demande.getStatus()==Constante.ETAT_DEMANDE_EN_COURS)
        		demande.setStatus(Constante.ETAT_DEMANDE_PROVISOIRE);
        	demandeScoringRepository.save(demande);
        	return true;
		}
        catch (Exception e){
        	throw new DemandeException(e.getMessage(), e);
		}
    }
    
    @Override
	public DemandeScoringDTO getDemandeBystatus(Long idEntreprise, int statutDemande) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findDemandeByStatus(idEntreprise, statutDemande);
		return dtoFactory.createDemandeScoring(demande);
	}
    

    @Override
    public boolean cloturerDemande(Long id) throws DemandeException {
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
        try{
        	if(demande.getStatus()==Constante.ETAT_DEMANDE_PROVISOIRE){
        		demande.setStatus(Constante.ETAT_DEMANDE_CLOTURE);
                demande.setRapportGenere(true);
            }

        	demandeScoringRepository.save(demande);
        	return true;
		}
        catch (Exception e){
        	throw new DemandeException(e.getMessage(), e);
		}
    }


    @Override
	public DemandeScoringDTO getDemandeNonClotureParEntreprise(Long idEntreprise) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findDemandeNonClotureParEntreprise(idEntreprise);
    	DemandeScoringDTO dto = dtoFactory.createDemandeScoring(demande);
    	dto.setLibelleStatut(getLibelleStatutDemande(demande.getStatus()));
		return dto;
	}


	@Override
	public DemandeScoringDTO getDemandeOuverte(Long idEntreprise) {
		DemandeScoring demande = demandeScoringRepository.findByEntreprise_IdAndStatusNot(idEntreprise, Constante.ETAT_DEMANDE_CLOTURE);
		DemandeScoringDTO dto = dtoFactory.createDemandeScoring(demande);
		dto.setLibelleStatut(getLibelleStatutDemande(demande.getStatus()));
		return dto;
	}

    @Override
    public DemandeScoringDTO getDemandeLastClosed(Long idEntreprise) {
        DemandeScoring demandeScoring = demandeScoringRepository.findFirstByEntreprise_IdOrderByDateCreationDesc(idEntreprise);
        return dtoFactory.createDemandeScoring(demandeScoring);
    }
    
    @Override
    public String getLibelleStatutDemande(int statut) {
       String libelle="";
       switch(statut)
       {
       case 1:
    	   libelle="Brouillon";
    	   break;
       case 2:
    	   libelle="Envoyée";
    	   break;
       case 3:
    	   libelle="En cours";
    	   break;
       case 4:
    	   libelle="Rejetée";
    	   break;
       case 5:
    	   libelle="Provisoire";
    	   break;
       case 6:
    	   libelle="Clôturée";
       }
       return libelle;
    }
}
