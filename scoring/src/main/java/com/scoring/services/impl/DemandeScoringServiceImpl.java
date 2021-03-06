package com.scoring.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.scoring.config.AccessTokenDetails;
import com.scoring.models.Entreprise;
import com.scoring.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.CalculScoreException;
import com.scoring.exceptions.DemandeException;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.DemandeScoring;
import com.scoring.payloads.DemandePayload;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.services.ICalculScoreService;
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
	
	@Autowired
	private ICalculScoreService calculScoreService;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

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

			DemandeScoring demandeScoring = demandeScoringRepository.findByEntreprise_IdAndStatusNotIn(idEntreprise, Arrays.asList(Constante.ETAT_DEMANDE_CLOTUREE, Constante.ETAT_DEMANDE_REJETEE, Constante.ETAT_DEMANDE_ANNULEE));
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
            demande.setStatus(Constante.ETAT_DEMANDE_ENVOYEE);
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
		AccessTokenDetails user = userService.getConnectedUser();
        try{
        	demande.setStatus(Constante.ETAT_DEMANDE_EN_COURS);
        	demande.setTraiterPar(user.getUserId());
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
        		demande.setStatus(Constante.ETAT_DEMANDE_REJETEE);
        	demande.setMotif(demandePayload.getMotif_rejet());
        	demandeScoringRepository.save(demande);

        	Entreprise entreprise = entrepriseRepository.findById(demande.getEntreprise().getId()).orElse(null);
        	if(entreprise != null){
        		entreprise.setEligible(false);
        		entrepriseRepository.saveAndFlush(entreprise);
			}
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
        	if(demande.getStatus()==Constante.ETAT_DEMANDE_EN_COURS){
        		demande.setStatus(Constante.ETAT_DEMANDE_PROVISOIRE);
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
	public DemandeScoringDTO getDemandeBystatus(Long idEntreprise, int statutDemande) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findDemandeByStatus(idEntreprise, statutDemande);
		return dtoFactory.createDemandeScoring(demande);
	}

    @Override
    public boolean cloturerDemande(Long id) throws DemandeException {
        DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
        try{
        	if(demande.getStatus()==Constante.ETAT_DEMANDE_PROVISOIRE){
        		demande.setStatus(Constante.ETAT_DEMANDE_CLOTUREE);
                demande.setRapportGenere(true);
            }

        	demandeScoringRepository.save(demande);
        	//Reinitialiser entreprise
			Entreprise entreprise = entrepriseRepository.findById(demande.getEntreprise().getId()).orElse(null);
			if(entreprise != null){
				entreprise.setEligible(false);
				entrepriseRepository.saveAndFlush(entreprise);
			}
        	return true;
		}
        catch (Exception e){
        	throw new DemandeException(e.getMessage(), e);
		}
    }

    @Override
	public DemandeScoringDTO getDemandeNonClotureParEntreprise(Long idEntreprise) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findDemandeNonClotureParEntreprise(idEntreprise);
    	return dtoFactory.createDemandeScoring(demande);

	}

	@Override
	public DemandeScoringDTO getDemandeOuverte(Long idEntreprise) {
		DemandeScoring demande = demandeScoringRepository.findByEntreprise_IdAndStatusNotIn(idEntreprise, Arrays.asList(Constante.ETAT_DEMANDE_CLOTUREE, Constante.ETAT_DEMANDE_REJETEE, Constante.ETAT_DEMANDE_ANNULEE));
		return dtoFactory.createDemandeScoring(demande);
	}

    @Override
    public DemandeScoringDTO getDemandeLastClosed(Long idEntreprise) {
        DemandeScoring demandeScoring = demandeScoringRepository.findFirstByEntreprise_IdOrderByDateCreationDesc(idEntreprise);
        return dtoFactory.createDemandeScoring(demandeScoring);
    }
    
    @Override
    public DemandeScoringDTO getDemandeEnvoyee(Long idEntreprise) throws DemandeException {
    	DemandeScoring demande = demandeScoringRepository.findDemandeEnvoyee(idEntreprise);
    	DemandeScoringDTO dto = dtoFactory.createDemandeScoring(demande);
    	if(dto!=null){
    		ScoresParPMEDTO scoreDTO = null;
			try {
				scoreDTO = calculScoreService.getScoreFinal(dto.getId());
			} catch (CalculScoreException e) {
				e.printStackTrace();
				dto.setScoreFinal(0);
			}
    		if(scoreDTO!=null) 
    			dto.setScoreFinal(scoreDTO.getScore_final());
    	}
        return dto;
    }

	@Override
	public DemandeScoringDTO getUserDemandeOuverte(Long idUser) {
		UserDTO user = userService.getUserById(idUser);
		return getDemandeLastClosed(user.getEntrepriseId());
	}

	@Override
	public boolean relancerDemandeScoring(Long id) throws DemandeException {
		DemandeScoring demande = demandeScoringRepository.findById(id).orElseThrow(() -> new DemandeException("Demande scoring :: "+id+" not found."));
		try{
			if(demande.getStatus()==Constante.ETAT_DEMANDE_ANNULEE){
				demande.setStatus(Constante.ETAT_DEMANDE_BROUILLON);
			}
			demandeScoringRepository.save(demande);
			return true;
		}
		catch (Exception e){
			throw new DemandeException(e.getMessage(), e);
		}
	}
}

