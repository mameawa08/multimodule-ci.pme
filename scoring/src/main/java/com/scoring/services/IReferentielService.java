package com.scoring.services;

import java.util.List;

import com.scoring.dto.*;
import com.scoring.exceptions.ReferentielException;

public interface IReferentielService {

    public FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException;

    public SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException;

	List<RatioDTO> getlisteRatios() throws Exception;

	CalibrageDTO getCalibrageByRatioAndValeurCalcule(Long idRatio, double valeurRatio) throws Exception;

	RatioDTO getRatioById(Long idRatio) throws Exception;

	QuestionDTO getQuestionById(Long idQuestion) throws Exception;

    List<PonderationDTO> getPonderations() throws ReferentielException;
}
