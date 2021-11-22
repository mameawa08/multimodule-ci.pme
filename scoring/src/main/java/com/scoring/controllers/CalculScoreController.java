package com.scoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.services.ICalculScoreService;


@RestController
@RequestMapping(value = "/api/score")
public class CalculScoreController {
	
	@Autowired
	private ICalculScoreService calculScoreService;

	@GetMapping("/financier/{idEntreprise}")
	public ResponseEntity<?> getScoreFinAndRatiosCalcules(@PathVariable Long idEntreprise) {
		try {
			ScoresAndRatioDTO scoreAndRatios = calculScoreService.getScoreFinAndRatios(idEntreprise);
			return ResponseEntity.ok(scoreAndRatios);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
