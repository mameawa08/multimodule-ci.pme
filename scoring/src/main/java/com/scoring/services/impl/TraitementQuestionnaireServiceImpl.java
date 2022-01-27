package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.scoring.dto.*;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IDemandeScoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Question;
import com.scoring.models.ReponseParPME;
import com.scoring.models.ReponseQualitative;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;
import com.scoring.payloads.ReponseParPMEPayload;
import com.scoring.payloads.ReponseQualitativePayload;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.repository.DirigeantRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.QuestionRepository;
import com.scoring.repository.ReponseParPMERepository;
import com.scoring.repository.ReponseQualitativeRepository;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IMailService;
import com.scoring.services.ITraitementQuestionnaireService;
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
		if(sendMail==false){
			entrepriseDTO.setEligible(false);
			DirigeantDTO dirigeantDTO = dtoFactory.createDirigeant(dirigeantRepository.findDirigeantByEntreprise(entrepriseDTO.getId()));
			iMailService.sendNotification(dirigeantDTO);
//			set demande status to 'elimine'
			demandeDTO.setStatus(Constante.ETAT_DEMANDE_ANNULEE);
		}else
			entrepriseDTO.setEligible(true);
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
	public ScoreEntrepriseParParametreDTO validateQuestionnaireQualitifByParametre(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException {
		List<ReponseParPME> reponses = new ArrayList<>();
		try {
			List<ReponseQualitativePayload> reps = payload.getListReponse();

			if (reps != null && reps.size() > 0){
				saveReponses(payload.getIdEntreprise(), reps);

				ScoreEntrepriseParParametreDTO scoreEntrepriseParParametreDTO = calculScoreService.calculScoreParametreQualitatif(0L, payload.getIdEntreprise());

				return  scoreEntrepriseParParametreDTO;

			}
			return null;

		} catch (Exception e) {
			throw new TraitementQuestionnaireException("Traitement questionnaire ::"+e.getMessage());
		}
	}

	@Override
	public ScoreEntrepriseParParametreDTO validateQuestionnaireQualitifByParametre(Long idParametre, QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException{
		List<ReponseParPME> reponses = new ArrayList<>();
		try {
			List<ReponseQualitativePayload> reps = payload.getListReponse();

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


//	Private methods
	private void saveReponses(Long idDemande, List<ReponseQualitativePayload> reps) throws TraitementQuestionnaireException {
		//Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: entreprise "+entrepriseId+" not found."));;
		DemandeScoring demande = demandeRepository.findById(idDemande).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaire :: demande "+idDemande+" not found."));
		List<ReponseParPMEDTO> reponseParPMEDTOs = new ArrayList<>();

		for (ReponseQualitativePayload rep : reps) {
	//					Get question
			Question question = questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: question "+rep.getIdQuestion()+" not found."));
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

}
