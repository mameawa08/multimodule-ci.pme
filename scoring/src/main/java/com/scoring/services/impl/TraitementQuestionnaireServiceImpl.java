package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.scoring.dto.*;
import com.scoring.models.*;
import com.scoring.payloads.*;
import com.scoring.repository.*;
import com.scoring.services.*;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.utils.Constante;

@Service
public class TraitementQuestionnaireServiceImpl implements ITraitementQuestionnaireService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private ReponseParPMERepository reponseParPMERepository;

	@Autowired
	private DirigeantRepository dirigeantRepository;

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private DemandeScoringRepository demandeRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IMailService iMailService;

	@Autowired
	private IEntrepriseService entrepriseService;

	@Autowired
	private ReponseQualitativeRepository reponseQualitativeRepository;

	@Autowired
	private ICalculScoreService calculScoreService;
	
	@Autowired
	private IDemandeScoring demandeScoringService;

	@Autowired
	private DemandeAccompagnementRepository demandeAccompagnementRepository;

	@Autowired
	private AccompagnementAEligibiliteRepository accompagnementAEligilibiteRepository;

	@Autowired
	private IReferentielService referentielService;

	@Autowired
	private IUserService userService;

    @Override
	public boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception {
		List<ReponseParPME> listReponseParPME = new ArrayList<ReponseParPME>();
		boolean sendMail=true;
		DemandeScoringDTO demandeDTO = null;
		EntrepriseDTO entrepriseDTO = null;
		if(questionnaireEliPayload.getIdEntreprise()!=null){
			entrepriseDTO = dtoFactory.createEntreprise(entrepriseRepository.findById(questionnaireEliPayload.getIdEntreprise()).orElseThrow(() -> new Exception("Not found.")));
			demandeDTO = demandeScoringService.getDemande(questionnaireEliPayload.getIdDemande());
		}
		for(ReponseParPMEPayload rep :questionnaireEliPayload.getListReponse()){
			ReponseParPMEDTO reponseDTO = new ReponseParPMEDTO();
			reponseDTO.setReponse_eligibilite(rep.isReponse());
			if(rep.getIdQuestion()!=null)
				reponseDTO.setIdQuestion(rep.getIdQuestion());
			reponseDTO.setDemandeScoringDTO(demandeDTO);
			ReponseParPME reponse_par_PME = modelFactory.createReponseParPME(reponseDTO);
			reponse_par_PME = reponseParPMERepository.save(reponse_par_PME);
			if(reponseDTO.isReponse_eligibilite()==false)
				sendMail=false;
			listReponseParPME.add(reponse_par_PME);
		}
		DirigeantDTO dirigeantDTO = dtoFactory.createDirigeant(dirigeantRepository.findDirigeantByEntreprise(entrepriseDTO.getId()));
        UserDTO connectedUser = userService.getConnectedUserInfos();
        String message = "";
		if(sendMail==false){
			entrepriseDTO.setEligible(false);
            message = "Votre entreprise n'est pas éligible !";
            sendNotification(dirigeantDTO, connectedUser, message);

//			set demande status to 'elimine'
			demandeDTO.setStatus(Constante.ETAT_DEMANDE_ANNULEE);
			demandeDTO.setMotif("PME non éligible");
		}
		else{
			entrepriseDTO.setEligible(true);
            message = "Votre entreprise est éligible !";
			sendNotification(dirigeantDTO, connectedUser, message);
		}
		if(demandeDTO!=null)
			demandeDTO.setRepEli(true);
		
		Entreprise entreprise = modelFactory.createEntreprise(entrepriseDTO);
		entreprise = entrepriseRepository.save(entreprise);
		DemandeScoring demande = modelFactory.createDemandeScoring(demandeDTO);
		demande = demandeRepository.save(demande);
		if(listReponseParPME.size()==questionnaireEliPayload.getListReponse().size())
			return true;
		else
			return false;
	}

    @Override
	public List<ReponseParPMEDTO> getListeRepQuestEli(Long idDemande) throws Exception {
		List<ReponseParPME> listReponses = reponseParPMERepository.findRepQuestEliByDemande(idDemande);
		List<ReponseParPMEDTO> listReponsesDTO = dtoFactory.createListReponseParPME(listReponses);
		return listReponsesDTO;
	}

	@Override
	public boolean validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException {
		List<ReponseParPMEDTO> reponseParPMEDTOs = new ArrayList<>();
		List<ReponseParPME> reponses = new ArrayList<>();
		try {
			Entreprise entreprise = entrepriseRepository.findById(payload.getIdEntreprise()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: entreprise "+payload.getIdEntreprise()+" not found."));;
			EntrepriseDTO entrepriseDTO = dtoFactory.createEntreprise(entreprise);
			DemandeScoringDTO demande = demandeScoringService.getDemande(payload.getIdDemande());
			List<ReponseQualitativePayload> reps = payload.getListReponse();

			if (reps != null && reps.size() > 0){
				saveReponses(payload.getIdDemande(), reps);

				calculScoreService.calculScoreParametreQualitatif(demande.getId());

				calculScoreService.calculScoreFinale(demande.getId());
			}

		} catch (Exception e) {
			throw new TraitementQuestionnaireException("Traitement questionnaire ::"+e.getMessage());
		}

		return true;
	}

	@Override
	public ScoreEntrepriseParParametreDTO validateQuestionnaireQualitifByParametre(Long idParametre, QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException{
		List<ReponseParPME> reponses = new ArrayList<>();
		try {
			List<ReponseQualitativePayload> reps = payload.getListReponse();
//			Nombre de question du parametre
			ParametreDTO parametre = referentielService.getParamtre(idParametre);
//			verifier le nombre de question avec une reponse "ne s'applique pas"
			checkReponse(parametre.getNbre_question(), reps);
//			Enregistrement et calcul du  score du parametre
			if (reps != null && reps.size() > 0){
				saveReponses(payload.getIdDemande(), reps);

				ScoreEntrepriseParParametreDTO scoreEntrepriseParParametreDTO = calculScoreService.calculScoreParametreQualitatif(payload.getIdDemande(), idParametre);

				return  scoreEntrepriseParParametreDTO;
			}
			return null;

		} catch (Exception e) {
			throw new TraitementQuestionnaireException("Traitement questionnaire ::"+e.getMessage());
		}
	}

	@Override
	public List<ReponseParPMEDTO> getListeReponseQuestionQUalitatif(Long idDemande) throws TraitementQuestionnaireException {
		//Entreprise entreprise = entrepriseRepository.findById(idEntreprise).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: entreprise "+idEntreprise+" not found."));;
		DemandeScoring demandeScoring = demandeRepository.findById(idDemande).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaire :: demande "+idDemande+" not found."));
		List<ReponseParPME> reponses = reponseParPMERepository.findReponseParPMEQualitatifByDemande(demandeScoring.getId());
		return dtoFactory.createListReponseParPME(reponses);
	}

	@Override
	public List<QuestionDTO> getListReponseQuestionEligibiliteNon(Long idDemande){
		List<ReponseParPME> reponses = reponseParPMERepository.findByDemandeScoring_IdAndReponseEligibilite(idDemande, false);
        List<QuestionDTO> questions = new ArrayList<>();
		try {
            for (ReponseParPME reponse : reponses){
                QuestionDTO question = referentielService.getQuestionById(reponse.getIdQuestion());
                questions.add(question);
            }
        }
		catch (Exception e){
		    e.printStackTrace();
        }
		return questions;
	}

	@Override
	public boolean traiterQuestionnaireAccompagnement(AccompagnementPayload payload) throws TraitementQuestionnaireException{
		DemandeAccompagnement demandeAccompagnement = demandeAccompagnementRepository.findById(payload.getIdDemandeAccompagnement())
				.orElseThrow(() -> new TraitementQuestionnaireException("Demande accompagnement n'existe pas."));
		AccompagnementAEligibilte accompagnement;
		try {
			for(ReponseAccompagnement reponse : payload.getReponses()){
				accompagnement = accompagnementAEligilibiteRepository
						.findByDemandeAccompagnement_IdAndQuestionEligibilite(payload.getIdDemandeAccompagnement(), reponse.getIdQuestion());
				if(accompagnement == null){
					accompagnement = new AccompagnementAEligibilte();
					accompagnement.setDemandeAccompagnement(demandeAccompagnement);
					accompagnement.setAccompagnement(reponse.getAccompagnement());
					accompagnement.setQuestionEligibilite(reponse.getIdQuestion());
				}
				else{
					accompagnement.setAccompagnement(reponse.getAccompagnement());
				}
				accompagnementAEligilibiteRepository.saveAndFlush(accompagnement);

				demandeAccompagnement.setQuestionnaireAjoute(true);
				demandeAccompagnementRepository.saveAndFlush(demandeAccompagnement);
			}
			return true;
		}
		catch (Exception e){
			throw new TraitementQuestionnaireException(e.getMessage());
		}
	}

	@Override
	public List<AccompagnementAEligibilteDTO> getReponseAccompagnement(Long idDemandeAccompagnement){
    	List<AccompagnementAEligibilte> aEligibiltes = accompagnementAEligilibiteRepository.findByDemandeAccompagnement_Id(idDemandeAccompagnement);
    	return dtoFactory.createListAccompagnementAEligibilte(aEligibiltes);
	}

//	Private methods
	private void saveReponses(Long idDemande, List<ReponseQualitativePayload> reps) throws TraitementQuestionnaireException {
		DemandeScoring demande = demandeRepository.findById(idDemande).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaire :: demande "+idDemande+" not found."));
		List<ReponseParPMEDTO> reponseParPMEDTOs = new ArrayList<>();

		for (ReponseQualitativePayload rep : reps) {
	//					Get question
			Question question = questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: questionEligibilite "+rep.getIdQuestion()+" not found."));
			QuestionDTO questionDTO = dtoFactory.createQuestion(question);

	//					Get Reponse qualitative
			ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(rep.getReponse()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: reponse "+rep.getReponse()+" not found."));
			ReponseQualitativeDTO reponseQualitativeDTO = dtoFactory.createReponseQualitative(reponseQualitative);

	//					Existing reponse par pme
			ReponseParPMEDTO reponseParPMEDTO;
			ReponseParPME reponseParPME = reponseParPMERepository.findByDemandeScoringAndIdQuestion(demande, question.getId()).orElse(null);
			reponseParPMEDTO = dtoFactory.createReponseParPME(reponseParPME);
	//					New reponse par pme dto
			if(reponseParPME == null)
				reponseParPMEDTO = new ReponseParPMEDTO();

			reponseParPMEDTO.setDemandeScoringDTO(dtoFactory.createDemandeScoring(demande));
			reponseParPMEDTO.setIdQuestion(questionDTO.getId());
			reponseParPMEDTO.setId_reponse_quali(reponseQualitativeDTO.getId());

			reponseParPMEDTOs.add(reponseParPMEDTO);
		}

		List<ReponseParPME> reponses = modelFactory.createListReponseParPME(reponseParPMEDTOs);

		reponseParPMERepository.saveAll(reponses);

		demande.setRepQuali(true);

		demandeRepository.save(demande);
	}

    private void sendNotification(DirigeantDTO dirigeantDTO, UserDTO connectedUser, String message) throws Exception {
        if(dirigeantDTO.getEmail().equals(connectedUser.getEmail())){
            DestinataireDTO dest = dtoFactory.createDestinataire(dirigeantDTO);
            iMailService.sendNotification(dest, message);
        }
        else {
            DestinataireDTO dest = dtoFactory.createDestinataire(dirigeantDTO);
            iMailService.sendNotification(dest, message);

            DestinataireDTO dest1 = dtoFactory.createDestinataire(connectedUser);
            dest1.setNomEntreprise(dirigeantDTO.getEntreprise().getRaisonSociale());
            iMailService.sendNotification(dest1, message);
        }
    }

    private void checkReponse(int nbQuestion, List<ReponseQualitativePayload> reps) throws TraitementQuestionnaireException {
		if(nbQuestion == (int)reps.stream().filter(r -> r.getReponse().equals(Constante.REPONSE_NE_SAPPLIQUE_PAS)).count()){
			throw new TraitementQuestionnaireException("Il faut au moins une question qui s'applique à la PME");
		}
	}
}
