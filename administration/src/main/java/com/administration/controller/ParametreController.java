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

import com.administration.dto.ParametreDTO;
import com.administration.exception.ParametreException;
import com.administration.payload.ParametrePayload;
import com.administration.service.IParametreService;






/**
 * 
 * RestFul API Paramètres Qualitatifs.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/parametres")
public class ParametreController {
	
	@Autowired
	private IParametreService parametreService;

	@GetMapping(value = "")
	public ResponseEntity<List<ParametreDTO>> getListeParametres() throws Exception {
       List<ParametreDTO> parametreListes = parametreService.getListeParametres();
       return ResponseEntity.ok(parametreListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<ParametreDTO>> getListeParametresActif() throws Exception {
       List<ParametreDTO> parametreListes = parametreService.getListeParametresActifs();
       return ResponseEntity.ok(parametreListes);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createParametre(@RequestBody ParametrePayload parametrePayload) {
		try {
			ParametreDTO parametre = parametreService.createParametre(parametrePayload);
			return ResponseEntity.ok(parametre);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updateParametre(@RequestBody ParametrePayload parametrePayload) {
		try {
			ParametreDTO parametre = parametreService.createParametre(parametrePayload);
			return ResponseEntity.ok(parametre);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteParametre(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = parametreService.deleteParametre(id);
			return ResponseEntity.ok(deleted);
		} catch (ParametreException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
