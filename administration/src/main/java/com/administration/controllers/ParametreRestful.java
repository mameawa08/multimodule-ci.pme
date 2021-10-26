package com.administration.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administration.modele.Parametre;
import com.administration.service.IParametreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


/**
 * 
 * RestFul API Paramètres Qualitatifs.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/parametres")
public class ParametreRestful {
	
	@Autowired
	private IParametreService parametreService;

	@GetMapping(value = "")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"),summary = "Liste des paramètres",
			description = "API retourne la liste des parametres", tags = {"Parametre"})
	public ResponseEntity<?> getListeParametres ()  {
       List<ParametreDTO> parametreListes= null;
        try {
        	parametreListes = parametreService.getListeParametres();
            return ResponseEntity.ok(parametreListes);
        } catch (Exception e) {
        	return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

	
}
