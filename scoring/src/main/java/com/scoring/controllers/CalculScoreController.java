package com.scoring.controllers;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.exceptions.CalculScoreException;
import com.scoring.models.ScoresParPME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.services.ICalculScoreService;

import java.util.List;


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

	@GetMapping("/qualitatif/{idEntreprise}/calcul")
	public ResponseEntity<?> calculScoreQualitatif(@PathVariable Long idEntreprise) {
		try {
			List<ScoreEntrepriseParParametreDTO> scores = calculScoreService.calculScoreParametreQualitatif(idEntreprise);
			return ResponseEntity.ok(scores);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/qualitatif/{idEntreprise}")
	public ResponseEntity<?> getScoreQualitatif(@PathVariable Long idEntreprise) {
		try {
			List<ScoreEntrepriseParParametreDTO> scores = calculScoreService.getScoreEntrepriseParParametre(idEntreprise);
			return ResponseEntity.ok(scores);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/final/{id}")
	public ResponseEntity<?> getScoreFinal(@PathVariable Long id){
		try {
			ScoresParPMEDTO scoresParPMEDTO = calculScoreService.getScoreFinal(id);
			return ResponseEntity.ok(scoresParPMEDTO);
		} catch (CalculScoreException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}



}
