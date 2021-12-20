package com.scoring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.payloads.DemandePayload;
import com.scoring.payloads.RapportPayload;
import com.scoring.services.IDemandeScoring;


@RestController
@RequestMapping(value = "/api/demandes/scoring")
public class DemandeScoringController {
	
	@Autowired
	private IDemandeScoring demandeScoringService;


	@GetMapping("")
	public ResponseEntity getDemandes(){
		try {
			List<DemandeScoringDTO> demandes = demandeScoringService.getDemandes();
			return ResponseEntity.ok(demandes);
		}
		catch (DemandeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getDemande(@PathVariable Long id){
		try {
			DemandeScoringDTO demande = demandeScoringService.getDemande(id);
			return ResponseEntity.ok(demande);
		}
		catch (DemandeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PostMapping("/{idEntreprise}")
	public ResponseEntity createDemande(@PathVariable Long idEntreprise){
		try {
			DemandeScoringDTO demande = demandeScoringService.createDemande(idEntreprise);
			return ResponseEntity.ok(demande);
		}
		catch (DemandeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/envoyer")
	public ResponseEntity envoyerDemande(@PathVariable Long id){
		try {
			boolean rs = demandeScoringService.envoyerDemande(id);
			return ResponseEntity.ok(rs);
		} catch (DemandeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/receptionner")
	public ResponseEntity receptionnerDemande(@PathVariable Long id){
		try {
			boolean rs = demandeScoringService.receptionnerDemande(id);
			return ResponseEntity.ok(rs);
		} catch (DemandeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/rejet")
	public ResponseEntity rejeterDemande(@PathVariable Long id, @RequestBody DemandePayload demandePayload){
		try {
			boolean rs = demandeScoringService.rejeterDemande(id, demandePayload);
			return ResponseEntity.ok(rs);
		} catch (DemandeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/cloture")
	public ResponseEntity cloturerDemande(@PathVariable Long id){
		try {
			boolean rs = demandeScoringService.cloturerDemande(id);
			return ResponseEntity.ok(rs);
		} catch (DemandeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
