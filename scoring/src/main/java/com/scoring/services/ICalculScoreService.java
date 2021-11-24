package com.scoring.services;

import java.util.List;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ValeurRatioDTO;
import com.scoring.exceptions.CalculScoreException;

public interface ICalculScoreService {


	List<ValeurRatioDTO> calculRatios(Long idEntreprise) throws Exception;

	ScoresAndRatioDTO getScoreFinAndRatios(Long idEntreprise) throws Exception;


    List<ScoreEntrepriseParParametreDTO> calculScoreParametreQualitatif(Long id) throws CalculScoreException;
}
