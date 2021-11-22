package com.scoring.services;

import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;

public interface ITraitementQuestionnaireService {

	boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception;


    boolean validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException;
}
