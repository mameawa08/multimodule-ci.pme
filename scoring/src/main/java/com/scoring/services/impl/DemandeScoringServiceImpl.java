package com.scoring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.services.IDemandeScoring;


@Service
public class DemandeScoringServiceImpl implements IDemandeScoring {

	@Autowired
	private DemandeScoringRepository demandeScoringRepository;
	
	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;
	
	@Value("${app.administration.url}")
    private String appUrl;

	

	
}
