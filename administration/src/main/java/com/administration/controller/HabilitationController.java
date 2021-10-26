package com.administration.controller;

import java.util.List;

import com.administration.dto.HabilitationDTO;
import com.administration.exception.HabilitationException;
import com.administration.service.IHabilitationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabilitationController {
	
	@Autowired
	private IHabilitationService habilitationService;


	@GetMapping("")
	public ResponseEntity all(){
		try {
			List<HabilitationDTO> habilitations = habilitationService.getHabilitations();
			return ResponseEntity.ok(habilitations);
		} catch (HabilitationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity show(@RequestParam Long id){
		try {
			HabilitationDTO habilitation = habilitationService.getHabilitation(id);
			return ResponseEntity.ok(habilitation);
		} catch (HabilitationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
