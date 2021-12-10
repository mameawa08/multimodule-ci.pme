package com.scoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.services.IDemandeScoring;


@RestController
@RequestMapping(value = "/api/demande/scoring")
public class DemandeScoringController {
	
	@Autowired
	private IDemandeScoring demandeScoringService;


	


}
