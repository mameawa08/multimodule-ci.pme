package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.ReponseParPME;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.ReponseParPMEPayload;
import com.scoring.repository.DirigeantRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repositories.QuestionRepository;
import com.scoring.repositories.ReponseParPMERepository;
import com.scoring.services.IMailService;
import com.scoring.services.ITraitementQuestionnaireService;

@Service
public class TraitementQuestionnaireServiceImpl implements ITraitementQuestionnaireService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ReponseParPMERepository reponseParPMERepository;
	
	@Autowired
	private DirigeantRepository dirigeantRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private IMailService iMailService;
	
	@Override
	public boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception {
		List<ReponseParPME> listReponseParPME = new ArrayList<ReponseParPME>();
		boolean sendMail=true;
		EntrepriseDTO entrepriseDTO = null;
		for(ReponseParPMEPayload rep :questionnaireEliPayload.getListReponse()){
			ReponseParPMEDTO reponseDTO = new ReponseParPMEDTO();
			reponseDTO.setReponse_eligibilite(rep.isReponse());
			QuestionDTO questionDTO = null;
			if(rep.getIdQuestion()!=null)
				questionDTO = dtoFactory.createQuestion(questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new Exception("Not found.")));
			reponseDTO.setQuestionDTO(questionDTO);
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
			DirigeantDTO dirigeantDTO = dtoFactory.createDirigeant(dirigeantRepository.findDirigeantByEntreprise(entrepriseDTO.getId()));
			iMailService.sendNotification(dirigeantDTO);
		}
		if(listReponseParPME.size()==questionnaireEliPayload.getListReponse().size())
			return true;
		else
			return false;
	}


	

}
