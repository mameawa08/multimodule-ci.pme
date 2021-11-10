package com.scoring.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.Reponse_par_entreprise_DTO;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.Reponse_par_entreprise;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.Reponse_par_Entr_Payload;
import com.scoring.repositories.EntrepriseRepository;
import com.scoring.repositories.QuestionRepository;
import com.scoring.repositories.ReponseParPMERepository;
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
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Override
	public boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception {
		List<Reponse_par_entreprise> listReponseParPME = new ArrayList<Reponse_par_entreprise>();
		for(Reponse_par_Entr_Payload rep :questionnaireEliPayload.getListReponse()){
			Reponse_par_entreprise_DTO reponseDTO = new Reponse_par_entreprise_DTO();
			reponseDTO.setReponse_eligibilite(rep.isReponse());
			QuestionDTO questionDTO = null;
			if(rep.getIdQuestion()!=null)
				questionDTO = dtoFactory.createQuestion(questionRepository.findById(rep.getIdQuestion()).orElseThrow(() -> new Exception("Not found.")));
			reponseDTO.setQuestionDTO(questionDTO);
			EntrepriseDTO entrepriseDTO = null;
			if(questionnaireEliPayload.getIdEntreprise()!=null)
				entrepriseDTO = dtoFactory.createEntreprise(entrepriseRepository.findById(questionnaireEliPayload.getIdEntreprise()).orElseThrow(() -> new Exception("Not found.")));
			reponseDTO.setEntrepriseDTO(entrepriseDTO);
			Reponse_par_entreprise reponse_par_PME = modelFactory.createReponseParPME(reponseDTO);
			reponse_par_PME = reponseParPMERepository.save(reponse_par_PME);
			listReponseParPME.add(reponse_par_PME);
		}
		if(listReponseParPME.size()==questionnaireEliPayload.getListReponse().size())
			return false;
		else
			return true;
	}


	

}
