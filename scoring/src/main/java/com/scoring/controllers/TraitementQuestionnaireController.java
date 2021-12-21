package com.scoring.controllers;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.payloads.QuestionnaireQualitatifPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.models.ReponseParPME;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.services.ITraitementQuestionnaireService;


@RestController
@RequestMapping(value = "/api/traitement/questionnaire")
public class TraitementQuestionnaireController {

	@Autowired
	private ITraitementQuestionnaireService traitementQuestionnaireService;
	
	@RequestMapping(value = "/eligibilite", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<?> traitementEligibilite(@RequestBody QuestionnaireEliPayload questionnaireEliPayload) {
		try {
			boolean traite = traitementQuestionnaireService.validateQuestionnaireEli(questionnaireEliPayload);
			return ResponseEntity.ok(traite);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/liste-reponses/{idDemande}")
	public ResponseEntity<?> getListeReponseQuestEli(@PathVariable Long idDemande) {
		try {
			List<ReponseParPMEDTO> listeReponses = traitementQuestionnaireService.getListeRepQuestEli(idDemande);
			return ResponseEntity.ok(listeReponses);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/qualitatif", method = {RequestMethod.POST})
	public ResponseEntity<?> traitementQualitatif(@RequestBody QuestionnaireQualitatifPayload questionnaireEliPayload) {
		try {
			boolean traite = traitementQuestionnaireService.validateQuestionnaireQualitif(questionnaireEliPayload);
			return ResponseEntity.ok(traite);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/qualitatif/parametres/{id}", method = {RequestMethod.POST})
	public ResponseEntity<?> traitementUnParametreQualitatif(@PathVariable Long id, @RequestBody QuestionnaireQualitatifPayload questionnaireEliPayload) {
		try {
			ScoreEntrepriseParParametreDTO score = traitementQuestionnaireService.validateQuestionnaireQualitifByParametre(id, questionnaireEliPayload);
			return ResponseEntity.ok(score);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/liste-reponses/{idEntreprise}/qualitatif")
	public ResponseEntity<?> getListeReponseQuestionQualitatif(@PathVariable Long idEntreprise) {
		try {
			List<ReponseParPMEDTO> listeReponses = traitementQuestionnaireService.getListeReponseQuestionQUalitatif(idEntreprise);
			return ResponseEntity.ok(listeReponses);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
