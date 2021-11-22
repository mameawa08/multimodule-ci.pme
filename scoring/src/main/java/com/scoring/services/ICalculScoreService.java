package com.scoring.services;

import java.util.List;

import com.scoring.dto.ValeurRatioDTO;

public interface ICalculScoreService {


	List<ValeurRatioDTO> calculRatios(Long idEntreprise) throws Exception;

	
	
	
}
