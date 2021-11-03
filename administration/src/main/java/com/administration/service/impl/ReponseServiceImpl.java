package com.administration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.ReponseQualitativeDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Question;
import com.administration.model.ReponseQualitative;
import com.administration.payload.ReponsePayload;
import com.administration.repository.QuestionRepository;
import com.administration.repository.ReponseRepository;
import com.administration.service.IReponseService;


@Service("reponseService")
public class ReponseServiceImpl implements IReponseService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ReponseRepository reponseRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<ReponseQualitativeDTO> getListeReponses() {
		List<ReponseQualitative> reponses = reponseRepository.findAll();
		List<ReponseQualitativeDTO> reponsesDto = dtoFactory.createListReponseQualitative(reponses);
		return reponsesDto;
	}
	
	@Override
	public List<ReponseQualitativeDTO> getListeReponsesActif() {
		List<ReponseQualitative> reponses = reponseRepository.findAllActif();
		List<ReponseQualitativeDTO> reponsesDto = dtoFactory.createListReponseQualitative(reponses);
		return reponsesDto;
	}
	
	@Override
	public List<ReponseQualitativeDTO> getListeReponsesByQuestion(Long idQuestion) {
		List<ReponseQualitative> reponses = reponseRepository.findReponseByQuestion(idQuestion);
		List<ReponseQualitativeDTO> reponsesDto = dtoFactory.createListReponseQualitative(reponses);
		return reponsesDto;
	}
	
	@Override
	public ReponseQualitativeDTO createReponse(ReponsePayload reponsePayload) throws Exception {
		
		if (reponsePayload.getCode() == null || reponsePayload.getCode().equals("")) {
			throw new Exception("Le code de la réponse est obligatoire !");
		}
		if (reponsePayload.getLibelle() == null || reponsePayload.getLibelle().equals("")) {
			throw new Exception("Le libellé de la réponse est obligatoire !");
		}
		if (reponsePayload.getScore() == 0) {
			throw new Exception("Le score de la réponse est obligatoire !");
		}
		if (reponsePayload.getIdQuestion() == null) {
			throw new Exception("Veuillez choisir une question à rattacher à cette réponse !");
		}
		
		ReponseQualitativeDTO reponseDTO = new ReponseQualitativeDTO();
		reponseDTO.setId(reponsePayload.getId());
		reponseDTO.setCode(reponsePayload.getCode());
		reponseDTO.setLibelle(reponsePayload.getLibelle());
		reponseDTO.setScore(reponsePayload.getScore());
		Question question = questionRepository.findById(reponsePayload.getIdQuestion()).orElseThrow(() -> new Exception("Not found."));
		reponseDTO.setQuestionDTO(dtoFactory.createQuestion(question));
		reponseDTO.setActif(1);
		
		ReponseQualitative reponse = modelFactory.createReponseQualitative(reponseDTO);
		reponse = reponseRepository.save(reponse);
		return reponseDTO;
		
	}
	
	@Override
	public boolean deleteReponse(Long idReponse) throws Exception {
		try {
			if (idReponse == null) {
				throw new Exception("La réponse à supprimer est nulle !");
			}
			ReponseQualitative r = reponseRepository.findById(idReponse).orElseThrow(()-> new Exception("Not found."));
			if (r != null)
				reponseRepository.delete(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
