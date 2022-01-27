package com.administration.controller;

import java.util.List;

import com.administration.dto.NiveauEtudeDTO;
import com.administration.exception.ReferentielException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(value = "/secteurs-activites")
	public ResponseEntity<List<SecteurActiviteDTO>> getListeSecteurs() throws Exception {
       List<SecteurActiviteDTO> secteurListes = referentielService.getListeSecteurs();
       return ResponseEntity.ok(secteurListes);
	}

	@GetMapping(value = "/secteurs-activites/{id}")
	public ResponseEntity getSecteur(@PathVariable Long id) {
		try {
			SecteurActiviteDTO secteur = referentielService.getSecteurActivite(id);
			return ResponseEntity.ok(secteur);
		} catch (ReferentielException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/formes-juridiques")
	public ResponseEntity<List<FormeJuridiqueDTO>> getListeFormesJurd() throws Exception {
       List<FormeJuridiqueDTO> formesJurdListes = referentielService.getListeFormesJuridique();
       return ResponseEntity.ok(formesJurdListes);
	}

	@GetMapping(value = "/formes-juridiques/{id}")
	public ResponseEntity getListeFormesJurd(@PathVariable Long id)  {
		FormeJuridiqueDTO formeJur = null;
		try {
			formeJur = referentielService.getFormeJuridique(id);
			return ResponseEntity.ok(formeJur);
		} catch (ReferentielException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/niveaux-etudes")
	public ResponseEntity getListeNiveauEdute()  {
		List<NiveauEtudeDTO> niveauEtudes = referentielService.getListNiveauEtude();
		return ResponseEntity.ok(niveauEtudes);

	}

	@GetMapping(value = "/niveaux-etudes/{id}")
	public ResponseEntity getNiveauEdute(@PathVariable Long id){
		try {
			NiveauEtudeDTO niveauEtude = referentielService.getNiveauEtude(id);
			return ResponseEntity.ok(niveauEtude);
		} catch (ReferentielException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
