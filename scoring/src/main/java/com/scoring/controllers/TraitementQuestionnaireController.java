package com.scoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
	
	
	@GetMapping("/liste-reponses")
	public ResponseEntity<?> getListeReponseQuestEli(@PathVariable Long idEntreprise) {
		try {
			List<ReponseParPMEDTO> listeReponses = traitementQuestionnaireService.getListeRepQuestEli(idEntreprise);
			return ResponseEntity.ok(listeReponses);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
