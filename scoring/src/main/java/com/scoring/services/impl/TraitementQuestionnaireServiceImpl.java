package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.scoring.dto.*;
import com.scoring.services.ICalculScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.Entreprise;
import com.scoring.models.Question;
import com.scoring.models.ReponseParPME;
import com.scoring.models.ReponseQualitative;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;
import com.scoring.payloads.ReponseParPMEPayload;
import com.scoring.payloads.ReponseQualitativePayload;
import com.scoring.repository.DirigeantRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.QuestionRepository;
import com.scoring.repository.ReponseParPMERepository;
import com.scoring.repository.ReponseQualitativeRepository;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IMailService;
import com.scoring.services.ITraitementQuestionnaireService;

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

	@Override
	public boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception {
		List<ReponseParPME> listReponseParPME = new ArrayList<ReponseParPME>();
		boolean sendMail=true;
		EntrepriseDTO entrepriseDTO = null;
		for(ReponseParPMEPayload rep :questionnaireEliPayload.getListReponse()){
			ReponseParPMEDTO reponseDTO = new ReponseParPMEDTO();
			reponseDTO.setReponse_eligibilite(rep.isReponse());
			if(rep.getIdQuestion()!=null)
				reponseDTO.setIdQuestion(rep.getIdQuestion());
			if(questionnaireEliPayload.getIdEntreprise()!=null)
				entrepriseDTO = dtoFactory.createEntreprise(entrepriseRepository.findById(questionnaireEliPayload.getIdEntreprise()).orElseThrow(() -> new Exception("Not found.")));
			reponseDTO.setEntrepriseDTO(entrepriseDTO);
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
		}else
			entrepriseDTO.setEligible(true);
		entrepriseDTO.setRepEli(true);
		Entreprise entreprise = modelFactory.createEntreprise(entrepriseDTO);
		entreprise = entrepriseRepository.save(entreprise);
		if(listReponseParPME.size()==questionnaireEliPayload.getListReponse().size())
			return true;
		else
			return false;
	}


	@Override
	public List<ReponseParPMEDTO> getListeRepQuestEli(Long idEntreprise) throws Exception {
		List<ReponseParPME> listReponses = reponseParPMERepository.findRepQuestEliByEntreprise(idEntreprise);
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

			List<ReponseQualitativePayload> reps = payload.getListReponse();

			if (reps != null && reps.size() > 0){
				saveReponses(payload.getIdEntreprise(), reps);

				calculScoreService.calculScoreParametreQualitatif(entreprise.getId());

				calculScoreService.calculScoreFinale(entreprise.getId());
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
				Long parametreId = saveReponses(payload.getIdEntreprise(), reps);

				ScoreEntrepriseParParametreDTO scoreEntrepriseParParametreDTO = calculScoreService.calculScoreParametreQualitatif(parametreId, payload.getIdEntreprise());

				return  scoreEntrepriseParParametreDTO;

			}
			return null;

		} catch (Exception e) {
			throw new TraitementQuestionnaireException("Traitement questionnaire ::"+e.getMessage());
		}
	}


	@Override
	public List<ReponseParPMEDTO> getListeReponseQuestionQUalitatif(Long idEntreprise) throws TraitementQuestionnaireException {
		Entreprise entreprise = entrepriseRepository.findById(idEntreprise).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: entreprise "+idEntreprise+" not found."));;
		List<ReponseParPME> reponses = reponseParPMERepository.findReponseParPMEQualitatifByEntreprise(entreprise.getId());
		return dtoFactory.createListReponseParPME(reponses);
	}


//	Private methods
	private Long saveReponses(Long entrepriseId, List<ReponseQualitativePayload> reps) throws TraitementQuestionnaireException {
		Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: entreprise "+entrepriseId+" not found."));;
		List<ReponseParPMEDTO> reponseParPMEDTOs = new ArrayList<>();
		Long parametre = 0L;
		for (ReponseQualitativePayload rep : reps) {
	//					Get question
			Question question = questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: question "+rep.getIdQuestion()+" not found."));
			QuestionDTO questionDTO = dtoFactory.createQuestion(question);
			parametre = question.getParametre().getId();
	//					Get Reponse qualitative
			ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(rep.getReponse()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: reponse "+rep.getReponse()+" not found."));
			ReponseQualitativeDTO reponseQualitativeDTO = dtoFactory.createReponseQualitative(reponseQualitative);

	//					Existing reponse par pme
			ReponseParPMEDTO reponseParPMEDTO;
			ReponseParPME reponseParPME = reponseParPMERepository.findByEntrepriseAndIdQuestion(entreprise, question.getId()).orElse(null);
			reponseParPMEDTO = dtoFactory.createReponseParPME(reponseParPME);
	//					New reponse par pme dto
			if(reponseParPME == null)
				reponseParPMEDTO = new ReponseParPMEDTO();

			reponseParPMEDTO.setEntrepriseDTO(dtoFactory.createEntreprise(entreprise));
			reponseParPMEDTO.setIdQuestion(questionDTO.getId());
			reponseParPMEDTO.setId_reponse_quali(reponseQualitativeDTO.getId());

			reponseParPMEDTOs.add(reponseParPMEDTO);
		}

		List<ReponseParPME> reponses = modelFactory.createListReponseParPME(reponseParPMEDTOs);

		reponseParPMERepository.saveAll(reponses);

		entreprise.setRepQuali(true);

		entrepriseRepository.save(entreprise);

		return parametre;
	}

}
