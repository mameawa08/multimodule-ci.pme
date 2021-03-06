package com.scoring.services;

import java.util.List;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.dto.ValeurRatioDTO;
import com.scoring.exceptions.CalculScoreException;

public interface ICalculScoreService {


	List<ValeurRatioDTO> calculRatios(Long idEntreprise) throws Exception;

	ScoresAndRatioDTO getScoreFinAndRatios(Long idEntreprise) throws Exception;


    List<ScoreEntrepriseParParametreDTO> calculScoreParametreQualitatif(Long id) throws CalculScoreException;

    ScoreEntrepriseParParametreDTO calculScoreParametreQualitatif(Long id, Long parametreId) throws CalculScoreException;

    List<ScoreEntrepriseParParametreDTO> getScoreDemandeParParametre(Long id) throws CalculScoreException;

    ScoreEntrepriseParParametreDTO getScoreDemandeParParametre(Long idDemande, Long parametreId) throws CalculScoreException;

    ScoresParPMEDTO calculScoreFinale(Long id) throws CalculScoreException;

	ScoresParPMEDTO getScoreFinal(Long id) throws CalculScoreException;

    ScoresAndRatioDTO getScoreFinancierAndRatios(Long idDemande) ;
}
