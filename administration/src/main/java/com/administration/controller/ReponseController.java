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

import com.administration.dto.ReponseQualitativeDTO;
import com.administration.exception.ReponseException;
import com.administration.payload.ReponsePayload;
import com.administration.service.IReponseService;

/**
 * 
 * RestFul API Reponses.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/reponses")
public class ReponseController {
	
	@Autowired
	private IReponseService reponseService;

	@GetMapping(value = "")
	public ResponseEntity<List<ReponseQualitativeDTO>> getListeReponses() throws Exception {
       List<ReponseQualitativeDTO> reponseListes = reponseService.getListeReponses();
       return ResponseEntity.ok(reponseListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<ReponseQualitativeDTO>> getListeReponsesActif() throws Exception {
	       List<ReponseQualitativeDTO> reponseListes = reponseService.getListeReponsesActif();
	       return ResponseEntity.ok(reponseListes);
	}
	
	@GetMapping(value = "/question/{idQuestion}")
	public ResponseEntity<List<ReponseQualitativeDTO>> getListeReponsesByQuestion(@PathVariable Long idQuestion) throws Exception {
	       List<ReponseQualitativeDTO> reponseListes = reponseService.getListeReponsesByQuestion(idQuestion);
	       return ResponseEntity.ok(reponseListes);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createReponse(@RequestBody ReponsePayload reponsePayload) {
		try {
			ReponseQualitativeDTO reponse = reponseService.createReponse(reponsePayload);
			return ResponseEntity.ok(reponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updateReponse(@RequestBody ReponsePayload reponsePayload) {
		try {
			ReponseQualitativeDTO reponse = reponseService.createReponse(reponsePayload);
			return ResponseEntity.ok(reponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteReponse(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = reponseService.deleteReponse(id);
			return ResponseEntity.ok(deleted);
		} catch (ReponseException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
