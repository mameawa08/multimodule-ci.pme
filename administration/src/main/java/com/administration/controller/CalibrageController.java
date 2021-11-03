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

import com.administration.dto.CalibrageDTO;
import com.administration.exception.CalibrageException;
import com.administration.payload.CalibragePayload;
import com.administration.service.ICalibrageService;

/**
 * 
 * RestFul API Calibrages.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/calibrages")
public class CalibrageController {
	
	@Autowired
	private ICalibrageService calibrageService;

	@GetMapping(value = "")
	public ResponseEntity<List<CalibrageDTO>> getListeCalibrages() throws Exception {
       List<CalibrageDTO> calibragesListes = calibrageService.getListeCalibrages();
       return ResponseEntity.ok(calibragesListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<CalibrageDTO>> getListeCalibragesActif() throws Exception {
	       List<CalibrageDTO> calibragesListes = calibrageService.getListeCalibragesActif();
	       return ResponseEntity.ok(calibragesListes);
	}
	
	@GetMapping(value = "/ratio/{idRatio}")
	public ResponseEntity<List<CalibrageDTO>> getListeCalibragesByRatio(@RequestBody Long idRatio) throws Exception {
	       List<CalibrageDTO> calibragesListes = calibrageService.getListeCalibragesByRatio(idRatio);
	       return ResponseEntity.ok(calibragesListes);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createCalibrage(@RequestBody CalibragePayload calibragePayload) {
		try {
			CalibrageDTO calibrage = calibrageService.createCalibrage(calibragePayload);
			return ResponseEntity.ok(calibrage);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updateCalibrage(@RequestBody CalibragePayload calibragePayload) {
		try {
			CalibrageDTO calibrage = calibrageService.createCalibrage(calibragePayload);
			return ResponseEntity.ok(calibrage);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteCalibrage(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = calibrageService.deleteCalibrage(id);
			return ResponseEntity.ok(deleted);
		} catch (CalibrageException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
