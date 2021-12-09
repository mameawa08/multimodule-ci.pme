package com.scoring.controllers;

import java.util.List;

import com.scoring.dto.IndicateurDTO;
import com.scoring.dto.PieceJointeDTO;
import com.scoring.exceptions.PieceJointeException;
import com.scoring.payloads.IndicateurPayload;
import com.scoring.services.IIndicateurService;
import com.scoring.services.IPieceJointeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/indicateurs")
public class IndicateurController {

	@Autowired
	private IIndicateurService indicateurService;

	@Autowired
	private IPieceJointeService pieceJointeService;
	
	@GetMapping("")
	public ResponseEntity<?> all() {
		try {
			List<IndicateurDTO> indicateurs = indicateurService.getIndicateurs();
			return ResponseEntity.ok(indicateurs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		try {
			IndicateurDTO indicateur = indicateurService.getIndicateur(id);
			return ResponseEntity.ok(indicateur);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody IndicateurPayload payload) {
		try {
			IndicateurDTO indicateur = indicateurService.createIndicateur(payload);
			return ResponseEntity.status(HttpStatus.CREATED).body(indicateur);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/status")
	public ResponseEntity<?> switchStatus(@PathVariable Long id) {
		try {
			boolean indicateur = indicateurService.switchStatus(id);
			return ResponseEntity.ok(indicateur);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody IndicateurPayload payload) {
		try {
			IndicateurDTO indicateur = indicateurService.createIndicateur(payload);
			return ResponseEntity.ok(indicateur);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/{id}/attachments")
	public ResponseEntity<?> addAttachment(@PathVariable Long id, @RequestParam MultipartFile[] files){
		try {
			boolean rs = pieceJointeService.createPieceJointe(id, files, false);
			return ResponseEntity.ok(rs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}/attachments")
	public ResponseEntity<?> getAttachments(@PathVariable Long id){
		try {
			List<PieceJointeDTO> rs = pieceJointeService.getPieceJointes(id);
			return ResponseEntity.ok(rs);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@DeleteMapping("/{id}/attachments/{idPiece}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Long id, @PathVariable Long idPiece){
        try {
            boolean rs = pieceJointeService.deleteAttachment(id, idPiece, false);
            return ResponseEntity.ok(rs);
        } catch (PieceJointeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/attachments/{nomPiece}")
    public ResponseEntity<?> updateAttachmentFilename(@PathVariable Long id, @PathVariable String nomPiece){
        try {
            boolean rs = pieceJointeService.updateNom(id,nomPiece);
            return ResponseEntity.ok(rs);
        } catch (PieceJointeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
