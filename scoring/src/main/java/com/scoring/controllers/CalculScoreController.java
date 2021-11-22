package com.scoring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.ValeurRatioDTO;
import com.scoring.services.ICalculScoreService;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IIndicateurService;


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
