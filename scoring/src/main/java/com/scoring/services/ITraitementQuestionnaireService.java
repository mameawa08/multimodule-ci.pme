package com.scoring.services;

import com.scoring.payloads.QuestionnaireEliPayload;

public interface ITraitementQuestionnaireService {

	boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception;
	
	
}
