package com.administration.service;

import java.util.List;

import com.administration.dto.QuestionDTO;
import com.administration.payload.QuestionPayload;

public interface IQuestionService {

	List<QuestionDTO> getListeQuestions();

	List<QuestionDTO> getListeQuestionsActif();

	List<QuestionDTO> getListeQuestionsEligibilite();

	List<QuestionDTO> getListeQuestionsQualitative();

	List<QuestionDTO> getListeQuestionsByParametre(Long idParametre);

	QuestionDTO createQuestion(QuestionPayload questionPayload) throws Exception;

	boolean deleteQuestion(Long idQuestion) throws Exception;

	

}
