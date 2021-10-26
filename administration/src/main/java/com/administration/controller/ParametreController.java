package com.administration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administration.model.Parametre;
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
