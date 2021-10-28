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

import com.administration.dto.QuestionDTO;
import com.administration.exception.ParametreException;
import com.administration.payload.QuestionPayload;
import com.administration.service.IQuestionService;

/**
 * 
 * RestFul API Questions.
 * 
 * @author agileway
 *
 */

@RestController
@RequestMapping(value = "/api/questions")
public class QuestionController {
	
	@Autowired
	private IQuestionService questionService;

	@GetMapping(value = "")
	public ResponseEntity<List<QuestionDTO>> getListeQuestions() throws Exception {
       List<QuestionDTO> questionListes = questionService.getListeQuestions();
       return ResponseEntity.ok(questionListes);
	}
	
	@GetMapping(value = "/actif")
	public ResponseEntity<List<QuestionDTO>> getListeQuestionsActif() throws Exception {
       List<QuestionDTO> questionListes = questionService.getListeQuestionsActif();
       return ResponseEntity.ok(questionListes);
	}
	
	@GetMapping(value = "/eligibilite")
	public ResponseEntity<List<QuestionDTO>> getListeQuestionsEligibilite() throws Exception {
       List<QuestionDTO> questionListes = questionService.getListeQuestionsEligibilite();
       return ResponseEntity.ok(questionListes);
	}
	
	@GetMapping(value = "/qualitatif")
	public ResponseEntity<List<QuestionDTO>> getListeQuestionsQualitative() throws Exception {
       List<QuestionDTO> questionListes = questionService.getListeQuestionsQualitative();
       return ResponseEntity.ok(questionListes);
	}
	
	@GetMapping(value = "/parametre/{idParametre}")
	public ResponseEntity<List<QuestionDTO>> getListeQuestionsByParametre(@RequestBody Long idParametre) throws Exception {
       List<QuestionDTO> questionListes = questionService.getListeQuestionsByParametre(idParametre);
       return ResponseEntity.ok(questionListes);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createQuestion(@RequestBody QuestionPayload questionPayload) {
		try {
			QuestionDTO question = questionService.createQuestion(questionPayload);
			return ResponseEntity.ok(question);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionPayload questionPayload) {
		try {
			QuestionDTO question = questionService.createQuestion(questionPayload);
			return ResponseEntity.ok(question);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long id) throws Exception{
		try {
			boolean deleted = questionService.deleteQuestion(id);
			return ResponseEntity.ok(deleted);
		} catch (ParametreException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}
