package com.scoring.controllers;

import java.util.List;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.ValeurRatioDTO;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IEntrepriseService;

import com.scoring.services.IIndicateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/score")
public class CalculScoreController {

	@Autowired
	private IEntrepriseService entrepriseService;

	@Autowired
	private IIndicateurService indicateurService;
	
	@Autowired
	private ICalculScoreService calculScoreService;

	@GetMapping("/ratios/{idEntreprise}")
	public ResponseEntity<?> getRatiosCalcules(@PathVariable Long idEntreprise) {
		try {
			List<ValeurRatioDTO> listValeurRatio = calculScoreService.calculRatios(idEntreprise);
			return ResponseEntity.ok(listValeurRatio);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
