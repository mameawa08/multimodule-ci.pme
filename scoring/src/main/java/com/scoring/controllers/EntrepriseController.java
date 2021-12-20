package com.scoring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.PieceJointeDTO;
import com.scoring.dto.RapportFile;
import com.scoring.exceptions.FileGenerationException;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.payloads.RapportPayload;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IFileGenerationService;
import com.scoring.services.IIndicateurService;
import com.scoring.services.IPieceJointeService;


@RestController
@RequestMapping(value = "/api/entreprises")
public class EntrepriseController {

	@Autowired
	private IEntrepriseService entrepriseService;

	@Autowired
	private IIndicateurService indicateurService;

	@Autowired
	private IFileGenerationService fileGenerationService;

	@Autowired
	private IPieceJointeService pieceJointeService;

	@GetMapping("")
	public ResponseEntity<?> all() {
		try {
			List<EntrepriseDTO> entreprises = entrepriseService.getEntreprises();
			return ResponseEntity.ok(entreprises);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		try {
			EntrepriseDTO entreprise = entrepriseService.getEntreprise(id);
			return ResponseEntity.ok(entreprise);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/indicateurs")
	public ResponseEntity<?> getIndicateurs(@PathVariable Long id) {
		try {
			List<IndicateurDTO> indicateurs = indicateurService.getIndicateursByEntreprise(id);
			return ResponseEntity.ok(indicateurs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/indicateurs/{annee}")
	public ResponseEntity<?> getIndicateurByAnnee(@PathVariable Long id, @PathVariable int annee) {
		try {
			IndicateurDTO indicateur = indicateurService.getIndicateursByEntrepriseAndAnnee(id, annee);
			return ResponseEntity.ok(indicateur);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody EntreprisePayload payload) {
		try {
			EntrepriseDTO entreprise = entrepriseService.createEntreprise(payload);
			return ResponseEntity.status(HttpStatus.CREATED).body(entreprise);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<?> switchStatus(@PathVariable Long id) {
		try {
			boolean entreprise = entrepriseService.switchStatus(id);
			return ResponseEntity.ok(entreprise);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EntreprisePayload payload) {
		try {
			EntrepriseDTO entreprise = entrepriseService.createEntreprise(payload);
			return ResponseEntity.ok(entreprise);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{id}/rapport")
	public ResponseEntity generateReport(@PathVariable Long id, @RequestBody RapportPayload payload){
		try {
			RapportFile file = fileGenerationService.generateRapport(id, payload);
			return ResponseEntity.ok(file);
		} catch (FileGenerationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{id}/attachments")
	public ResponseEntity<?> addAttachment(@PathVariable Long id, @RequestParam MultipartFile[] files){
		try {
			boolean rs = pieceJointeService.createPieceJointe(id, files, true);
			return ResponseEntity.ok(rs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/attachments")
	public ResponseEntity<?> getAttachments(@PathVariable Long id){
		try {
			List<PieceJointeDTO> rs = pieceJointeService.getEntrepriseLogo(id);
			return ResponseEntity.ok(rs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
