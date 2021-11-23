package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.dto.ReponseQualitativeDTO;
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

	public boolean validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException {
		List<ReponseParPMEDTO> reponseParPMEDTOs = new ArrayList<>();
		List<ReponseParPME> reponses = new ArrayList<>();
		try {
			EntrepriseDTO entrepriseDTO = entrepriseService.getEntreprise(payload.getIdEntreprise());

			List<ReponseQualitativePayload> reps = payload.getListReponse();

			if (reps != null && reps.size() > 0){
				for (ReponseQualitativePayload rep : reps) {
//					Get question
					Question question = questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: question "+rep.getIdQuestion()+" not found."));
					QuestionDTO questionDTO = dtoFactory.createQuestion(question);

//					Get Reponse qualitative
					ReponseQualitative reponseQualitative = reponseQualitativeRepository.findById(rep.getReponse()).orElseThrow(() -> new TraitementQuestionnaireException("Traitement questionnaaire :: reponse "+rep.getReponse()+" not found."));
					ReponseQualitativeDTO reponseQualitativeDTO = dtoFactory.createReponseQualitative(reponseQualitative);

//					New reponse par pme dto
					ReponseParPMEDTO reponseParPMEDTO = new ReponseParPMEDTO();
					reponseParPMEDTO.setEntrepriseDTO(entrepriseDTO);
					reponseParPMEDTO.setIdQuestion(questionDTO.getId());
					reponseParPMEDTO.setId_reponse_quali(reponseQualitativeDTO.getId());

					reponseParPMEDTOs.add(reponseParPMEDTO);
				}

				reponses = modelFactory.createListReponseParPME(reponseParPMEDTOs);

				reponseParPMERepository.saveAll(reponses);
			}

		} catch (Exception e) {
			throw new TraitementQuestionnaireException("Traitement questionnaire ::"+e.getMessage());
		}

		return true;
	}

	

}
