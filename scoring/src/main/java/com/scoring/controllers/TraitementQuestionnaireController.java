package com.scoring.controllers;

import com.scoring.dto.AccompagnementAEligibilteDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.payloads.AccompagnementPayload;
import com.scoring.payloads.QuestionnaireQualitatifPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.scoring.dto.ReponseParPMEDTO;
import com.scoring.models.ReponseParPME;
import com.scoring.payloads.QuestionnaireEliPayload;
import com.scoring.services.ITraitementQuestionnaireService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

	@GetMapping("/liste-reponses/{idDemande}/qualitatif")
	public ResponseEntity<?> getListeReponseQuestionQualitatif(@PathVariable Long idDemande) {
		try {
			List<ReponseParPMEDTO> listeReponses = traitementQuestionnaireService.getListeReponseQuestionQUalitatif(idDemande);
			return ResponseEntity.ok(listeReponses);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value="/liste-questions-non-reponses/{idDemandeScoring}")
	public  ResponseEntity getListeQuestionEligibiliteNonReponse(@PathVariable Long  idDemandeScoring){
		List<QuestionDTO> reponses = traitementQuestionnaireService.getListReponseQuestionEligibiliteNon(idDemandeScoring);
		return ResponseEntity.ok(reponses);
	}
	
	@PostMapping(value="/accompagnement")
	public  ResponseEntity traiterQuestionnaireAccompagnement(@RequestBody AccompagnementPayload payload) {
		try {
		    boolean rs = traitementQuestionnaireService.traiterQuestionnaireAccompagnement(payload);
		    return ResponseEntity.ok(rs);
        }
		catch (Exception e){
		    return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

	@GetMapping("/liste-reponses/accompagnement/{idDemandeAccompagnement}")
	public ResponseEntity getReponseAccompagnement(@PathVariable Long idDemandeAccompagnement){
	    List<AccompagnementAEligibilteDTO> aEligibilteDTOS = traitementQuestionnaireService.getReponseAccompagnement(idDemandeAccompagnement);
	    return ResponseEntity.ok(aEligibilteDTOS);
    }
}
