package com.scoring.controllers;

import java.util.List;

import com.scoring.dto.RepondantDTO;
import com.scoring.payloads.RepondantPayload;
import com.scoring.services.IRepondantService;

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
@RequestMapping(value = "/api/repondants")
public class RepondantController {

	@Autowired
	private IRepondantService repondantService;
	
	@GetMapping("")
	public ResponseEntity<?> all() {
		try {
			List<RepondantDTO> repondants = repondantService.getRepondants();
			return ResponseEntity.ok(repondants);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		try {
			RepondantDTO repondant = repondantService.getRepondant(id);
			return ResponseEntity.ok(repondant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody RepondantPayload payload) {
		try {
			RepondantDTO repondant = repondantService.createRepondant(payload);
			return ResponseEntity.status(HttpStatus.CREATED).body(repondant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<?> switchStatus(@PathVariable Long id) {
		try {
			boolean repondant = repondantService.switchStatus(id);
			return ResponseEntity.ok(repondant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RepondantPayload payload) {
		try {
			RepondantDTO repondant = repondantService.createRepondant(payload);
			return ResponseEntity.ok(repondant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
