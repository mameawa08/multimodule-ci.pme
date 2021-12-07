package com.scoring.services;

import java.math.BigDecimal;
import java.util.List;

import com.scoring.dto.*;
import com.scoring.exceptions.ReferentielException;

public interface IReferentielService {

    public FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException;

    public SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException;

	List<RatioDTO> getlisteRatios() throws Exception;

	CalibrageDTO getCalibrageByRatioAndValeurCalcule(Long idRatio, BigDecimal valeurRatio) throws Exception;

	RatioDTO getRatioById(Long idRatio) throws Exception;

	QuestionDTO getQuestionById(Long idQuestion) throws Exception;

    List<PonderationDTO> getPonderations() throws ReferentielException;

    List<RatioDTO> getRatios() throws ReferentielException;

	PonderationDTO getPonderationByParametre(Long idParametre) throws Exception;

	PonderationDTO getPonderationScoreFinancier() throws Exception;

    ParametreDTO getParamtre(Long id) throws ReferentielException;

    List<ParametreDTO> getParamtres() throws ReferentielException;
}
