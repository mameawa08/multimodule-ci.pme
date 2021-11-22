package com.scoring.services;

import java.util.List;

import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.payloads.QuestionnaireEliPayload;

public interface ITraitementQuestionnaireService {

	boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception;

	List<ReponseParPMEDTO> getListeRepQuestEli(Long idEntreprise) throws Exception;
	
	
}
