package com.scoring.controllers;

import java.util.List;

import com.scoring.dto.DirigeantDTO;
import com.scoring.payloads.DirigeantPayload;
import com.scoring.services.IDirigeantService;

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


@RestController
@RequestMapping(value = "/api/dirigeants")
public class DirigeantController {

	@Autowired
	private IDirigeantService dirigeantService;
	
	@GetMapping("")
	public ResponseEntity<?> all() {
		try {
			List<DirigeantDTO> dirigeants = dirigeantService.getDirigeants();
			return ResponseEntity.ok(dirigeants);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/entreprise/{idEntreprise}")
	public ResponseEntity<?> getDirigeantByEntreprise(@PathVariable Long idEntreprise) {
		try {
			DirigeantDTO dirigeant = dirigeantService.getDirigeantByEntreprise(idEntreprise);
			return ResponseEntity.ok(dirigeant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		try {
			DirigeantDTO dirigeant = dirigeantService.getDirigeant(id);
			return ResponseEntity.ok(dirigeant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody DirigeantPayload payload) {
		try {
			DirigeantDTO dirigeant = dirigeantService.createDirigeant(payload);
			return ResponseEntity.status(HttpStatus.CREATED).body(dirigeant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<?> switchStatus(@PathVariable Long id) {
		try {
			boolean dirigeant = dirigeantService.switchStatus(id);
			return ResponseEntity.ok(dirigeant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DirigeantPayload payload) {
		try {
			DirigeantDTO dirigeant = dirigeantService.createDirigeant(payload);
			return ResponseEntity.ok(dirigeant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
