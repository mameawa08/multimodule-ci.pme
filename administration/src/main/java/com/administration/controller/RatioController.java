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

import com.administration.dto.RatioDTO;
import com.administration.exception.RatioException;
import com.administration.payload.RatioPayload;
import com.administration.service.IRatioService;

/**
 * 
 * RestFul API Ratios.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/ratios")
public class RatioController {
	
	@Autowired
	private IRatioService ratioService;

	@GetMapping(value = "")
	public ResponseEntity<List<RatioDTO>> getListeRatios() throws Exception {
       List<RatioDTO> ratioListes = ratioService.getListeRatios();
       return ResponseEntity.ok(ratioListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<RatioDTO>> getListeRatiosActif() throws Exception {
	       List<RatioDTO> ratioListes = ratioService.getListeRatiosActif();
	       return ResponseEntity.ok(ratioListes);
	}
	
	@GetMapping(value = "/code/{code}")
	public ResponseEntity<List<RatioDTO>> getListeRatiosParCode(@RequestBody String code) throws Exception {
	       List<RatioDTO> ratioListes = ratioService.getListeRatiosByCode(code);
	       return ResponseEntity.ok(ratioListes);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createRatio(@RequestBody RatioPayload ratioPayload) {
		try {
			RatioDTO ratio = ratioService.createRatio(ratioPayload);
			return ResponseEntity.ok(ratio);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updateRatio(@RequestBody RatioPayload ratioPayload) {
		try {
			RatioDTO ratio = ratioService.createRatio(ratioPayload);
			return ResponseEntity.ok(ratio);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteRatio(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = ratioService.deleteRatio(id);
			return ResponseEntity.ok(deleted);
		} catch (RatioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
