package com.scoring.services;


import java.util.List;

import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;

public interface ITraitementQuestionnaireService {

	boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception;
	List<ReponseParPMEDTO> getListeRepQuestEli(Long idEntreprise) throws Exception;
    boolean validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException;

    ScoreEntrepriseParParametreDTO validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload, Long parametreId) throws TraitementQuestionnaireException;

    List<ReponseParPMEDTO> getListeReponseQuestionQUalitatif(Long idEntreprise) throws TraitementQuestionnaireException;
}
