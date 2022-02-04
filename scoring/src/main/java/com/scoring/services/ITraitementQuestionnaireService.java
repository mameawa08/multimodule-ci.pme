package com.scoring.services;


import java.util.List;

import com.scoring.dto.AccompagnementAEligibilteDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.TraitementQuestionnaireException;
import com.scoring.payloads.AccompagnementPayload;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;

public interface ITraitementQuestionnaireService {

	boolean validateQuestionnaireEli(QuestionnaireEliPayload questionnaireEliPayload) throws Exception;

	List<ReponseParPMEDTO> getListeRepQuestEli(Long idDemande) throws Exception;

    boolean validateQuestionnaireQualitif(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException;

    ScoreEntrepriseParParametreDTO validateQuestionnaireQualitifByParametre(QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException;

    ScoreEntrepriseParParametreDTO validateQuestionnaireQualitifByParametre(Long idParametre, QuestionnaireQualitatifPayload payload) throws TraitementQuestionnaireException;

    List<ReponseParPMEDTO> getListeReponseQuestionQUalitatif(Long idDemande) throws TraitementQuestionnaireException;

    List<QuestionDTO> getListReponseQuestionEligibiliteNon(Long idDemande);

    boolean traiterQuestionnaireAccompagnement(AccompagnementPayload payload) throws TraitementQuestionnaireException;

    List<AccompagnementAEligibilteDTO> getReponseAccompagnement(Long idDemandeAccompagnement);
}
