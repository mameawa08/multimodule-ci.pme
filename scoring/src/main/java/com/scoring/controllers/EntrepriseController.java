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
import org.springframework.web.bind.annotation.RestController;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.services.IEntrepriseService;


@RestController
@RequestMapping(value = "/api/entreprises")
public class EntrepriseController {

	@Autowired
	private IEntrepriseService entrepriseService;
	
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
	
	
}
