package com.scoring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.dto.ScoresAndRatioDTO;
import com.scoring.dto.ScoresParPMEDTO;
import com.scoring.exceptions.CalculScoreException;
import com.scoring.services.ICalculScoreService;


@RestController
@RequestMapping(value = "/api/score")
public class CalculScoreController {
	
	@Autowired
	private ICalculScoreService calculScoreService;

	@GetMapping("/financier/{idDemande}")
	public ResponseEntity<?> getScoreFinAndRatiosCalcules(@PathVariable Long idDemande) {
		try {
			ScoresAndRatioDTO scoreAndRatios = calculScoreService.getScoreFinAndRatios(idDemande);
			return ResponseEntity.ok(scoreAndRatios);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/qualitatif/{idDemande}/calcul")
	public ResponseEntity<?> calculScoreQualitatif(@PathVariable Long idDemande) {
		try {
			List<ScoreEntrepriseParParametreDTO> scores = calculScoreService.calculScoreParametreQualitatif(idDemande);
			return ResponseEntity.ok(scores);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/qualitatif/{idDemande}/calcul/{parametreId}")
	public ResponseEntity<?> calculScoreQualitatif(@PathVariable Long idDemande, @PathVariable Long parametreId) {
		try {
			ScoreEntrepriseParParametreDTO score = calculScoreService.calculScoreParametreQualitatif(idDemande, parametreId);
			return ResponseEntity.ok(score);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/qualitatif/{idDemande}")
	public ResponseEntity<?> getScoreQualitatif(@PathVariable Long idDemande) {
		try {
			List<ScoreEntrepriseParParametreDTO> scores = calculScoreService.getScoreDemandeParParametre(idDemande);
			return ResponseEntity.ok(scores);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/qualitatif/{idDemande}/parametres/{parametreId}")
	public ResponseEntity<?> getScoreParametreQualitatif(@PathVariable Long idDemande, @PathVariable Long parametreId) {
		try {
			ScoreEntrepriseParParametreDTO scores = calculScoreService.getScoreDemandeParParametre(idDemande, parametreId);
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
