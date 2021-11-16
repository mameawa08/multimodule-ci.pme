package com.administration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administration.dto.FormeJuridiqueDTO;
import com.administration.dto.SecteurActiviteDTO;
import com.administration.service.IReferentielService;


/**
 * 
 * RestFul API Referentiels Techniques.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/referentiel")
public class ReferentielController {
	
	@Autowired
	private IReferentielService referentielService;

	@GetMapping(value = "/SecteurActivite")
	public ResponseEntity<List<SecteurActiviteDTO>> getListeSecteurs() throws Exception {
       List<SecteurActiviteDTO> secteurListes = referentielService.getListeSecteurs();
       return ResponseEntity.ok(secteurListes);
	}
	
	@GetMapping(value = "/FormeJuridique")
	public ResponseEntity<List<FormeJuridiqueDTO>> getListeFormesJurd() throws Exception {
       List<FormeJuridiqueDTO> formesJurdListes = referentielService.getListeFormesJuridique();
       return ResponseEntity.ok(formesJurdListes);
	}
	
	
	
}
