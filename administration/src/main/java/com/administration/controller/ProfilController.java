package com.administration.controller;

import java.util.List;

import com.administration.dto.ProfilDTO;
import com.administration.exception.ProfilException;
import com.administration.payload.ProfilPayload;
import com.administration.service.IProfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilController {
	
	@Autowired
	private IProfilService profilService;


	@GetMapping("")
	public ResponseEntity all(){
		try {
			List<ProfilDTO> profils = profilService.getProfils();
			return ResponseEntity.ok(profils);
		} catch (ProfilException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity show(@RequestParam Long id){
		try {
			ProfilDTO profil = profilService.getProfil(id);
			return ResponseEntity.ok(profil);
		} catch (ProfilException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity switchProfil(@RequestParam Long id){
		try {
			boolean rs = profilService.changerStatusProfil(id);
			return ResponseEntity.ok(rs);
		} catch (ProfilException e) {
			return ResponseEntity.badRequest().body(false);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity create(@RequestBody ProfilPayload payload){
		try {
			ProfilDTO profil = profilService.ajouterProfil(payload);
			return ResponseEntity.ok(profil);
		} catch (ProfilException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}





}
