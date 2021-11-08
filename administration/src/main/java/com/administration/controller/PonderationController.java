package com.administration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.administration.dto.Ponderation_scoreDTO;
import com.administration.exception.PonderationScoreException;
import com.administration.payload.PonderationPayload;
import com.administration.service.IPonderationService;

/**
 * 
 * RestFul API Ponderations.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/ponderations")
public class PonderationController {
	
	@Autowired
	private IPonderationService ponderationService;

	@GetMapping(value = "")
	public ResponseEntity<List<Ponderation_scoreDTO>> getListePonderations() throws Exception {
       List<Ponderation_scoreDTO> ponderationsListes = ponderationService.getListePonderations();
       return ResponseEntity.ok(ponderationsListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<Ponderation_scoreDTO>> getListePonderationsActif() throws Exception {
	       List<Ponderation_scoreDTO> ponderationsListes = ponderationService.getListePonderationsActif();
	       return ResponseEntity.ok(ponderationsListes);
	}
	
	@GetMapping(value = "/parametre/{idParametre}")
	public ResponseEntity<List<Ponderation_scoreDTO>> getListePonderationsByParametre(@PathVariable Long idParametre) throws Exception {
	       List<Ponderation_scoreDTO> ponderationsListes = ponderationService.getListePonderationsByParametre(idParametre);
	       return ResponseEntity.ok(ponderationsListes);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createPonderation(@RequestBody PonderationPayload ponderationPayload) {
		try {
			Ponderation_scoreDTO ponderation = ponderationService.createPonderation(ponderationPayload);
			return ResponseEntity.ok(ponderation);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updatePonderation(@RequestBody PonderationPayload ponderationPayload) {
		try {
			Ponderation_scoreDTO ponderation = ponderationService.createPonderation(ponderationPayload);
			return ResponseEntity.ok(ponderation);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deletePonderation(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = ponderationService.deletePonderation(id);
			return ResponseEntity.ok(deleted);
		} catch (PonderationScoreException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
