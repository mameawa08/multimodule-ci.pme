package com.scoring.services;

import java.util.List;

import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ValeurRatioDTO;

public interface ICalculScoreService {


	List<ValeurRatioDTO> calculRatios(Long idEntreprise) throws Exception;

	ScoresAndRatioDTO getScoreFinAndRatios(Long idEntreprise) throws Exception;

	
	
	
}
