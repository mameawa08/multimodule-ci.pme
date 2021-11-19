package com.administration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.QuestionDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Parametre;
import com.administration.model.Question;
import com.administration.payload.QuestionPayload;
import com.administration.repository.ParametreRepository;
import com.administration.repository.QuestionRepository;
import com.administration.service.IQuestionService;
import com.administration.service.IReponseService;




@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ParametreRepository parametreRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;
	
	@Autowired
	private IReponseService reponseService;

	@Override
	public List<QuestionDTO> getListeQuestions() {
		List<Question> questions = questionRepository.findAll();
		List<QuestionDTO> questionsDto = dtoFactory.createListQuestion(questions);
		return questionsDto;
	}
	
	@Override
	public List<QuestionDTO> getListeQuestionsActif() {
		List<Question> questions = questionRepository.findAllActif();
		List<QuestionDTO> questionsDto = dtoFactory.createListQuestion(questions);
		return questionsDto;
	}
	
	@Override
	public List<QuestionDTO> getListeQuestionsEligibilite() {
		List<Question> questions = questionRepository.findQuestionEligible();
		List<QuestionDTO> questionsDto = dtoFactory.createListQuestion(questions);
		return questionsDto;
	}
	
	@Override
	public List<QuestionDTO> getListeQuestionsQualitative() {
		List<Question> questions = questionRepository.findQuestionQualitative();
		List<QuestionDTO> questionsDto = dtoFactory.createListQuestion(questions);
		for(QuestionDTO q: questionsDto){
			q.setListReponsesDTO(reponseService.getListeReponsesByQuestion(q.getId()));
		}
		return questionsDto;
	}
	
	@Override
	public List<QuestionDTO> getListeQuestionsByParametre(Long idParametre) {
		List<Question> questions = questionRepository.findQuestionByParametre(idParametre);
		List<QuestionDTO> questionsDto = dtoFactory.createListQuestion(questions);
		for(QuestionDTO q: questionsDto){
			q.setListReponsesDTO(reponseService.getListeReponsesByQuestion(q.getId()));
		}
		return questionsDto;
	}
	
	@Override
	public QuestionDTO createQuestion(QuestionPayload questionPayload) throws Exception {
		int nbre;
		if (questionPayload.getLibelle() == null || questionPayload.getLibelle().equals("")) {
			throw new Exception("Le libellé de la question est obligatoire !");
		}
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setId(questionPayload.getId());
		questionDTO.setLibelle(questionPayload.getLibelle());
		Parametre param = null;
		if(questionPayload.getIdParametre()!=null) 
			param = parametreRepository.findById(questionPayload.getIdParametre()).orElseThrow(() -> new Exception("Not found."));
		if(questionDTO.getId()==null){
			if(questionPayload.getIdParametre()!=null){
				nbre = questionRepository.findNbreQuestionByParametre(questionPayload.getIdParametre())+1;
			}else
				nbre = questionRepository.findNBreQuestionEligible()+1;
			if(param!=null)
				questionDTO.setCode("Q"+nbre+param.getCode());
			else
				questionDTO.setCode("QE"+nbre);
		}else
			questionDTO.setCode(questionPayload.getCode());
		questionDTO.setParametreDTO(dtoFactory.createParametre(param));
		questionDTO.setActif(1);
		
		Question question = modelFactory.createQuestion(questionDTO);
		question = questionRepository.save(question);
		return questionDTO;
		
	}
	
	@Override
	public boolean deleteQuestion(Long idQuestion) throws Exception {
		try {
			if (idQuestion == null) {
				throw new Exception("La question à supprimer est nulle !");
			}
			Question q = questionRepository.findById(idQuestion).orElseThrow(()-> new Exception("Not found."));
			if (q != null)
				questionRepository.delete(q);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
