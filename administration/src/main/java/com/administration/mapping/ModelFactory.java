package com.administration.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.administration.dto.CalibrageDTO;
import com.administration.dto.HabilitationDTO;
import com.administration.dto.ParametreDTO;
import com.administration.dto.ProfilDTO;
import com.administration.dto.QuestionDTO;
import com.administration.dto.RatioDTO;
import com.administration.dto.ReponseQualitativeDTO;
import com.administration.model.Calibrage;
import com.administration.model.Habilitation;
import com.administration.model.Parametre;
import com.administration.model.Profil;
import com.administration.model.Question;
import com.administration.model.Ratio;
import com.administration.model.ReponseQualitative;

@Named("modelFactory")
public class ModelFactory {
	
	public Habilitation createHabilitation(HabilitationDTO habilitation){
		if(habilitation == null)
			return null;
		Habilitation model = new Habilitation();
		model.setId(habilitation.getId());
		model.setCode(habilitation.getCode());
		model.setLibelle(habilitation.getLibelle());

		return model;
	}

	public List<Habilitation> createListHabilitation(List<HabilitationDTO> habilitations){
		if(habilitations == null || (habilitations != null && habilitations.size() == 0))
			return new ArrayList<>();
		
		return habilitations.stream().map(this::createHabilitation).collect(Collectors.toList());
	}

	public Profil createProfil(ProfilDTO profil){
		if (profil == null) 
			return null;
		Profil model = new Profil();
		model.setId(profil.getId());
		model.setLibelle(profil.getLibelle());
		model.setCode(profil.getCode());
		model.setActif(profil.getActif());
		model.setHabilitations(createListHabilitation(profil.getHabilitations()));

		return model;
	}
	
	public Parametre createParametre(ParametreDTO parametreDTO){
		if (parametreDTO == null) 
			return null;
		Parametre parametre = new Parametre();
		parametre.setId(parametreDTO.getId());
		parametre.setCode(parametreDTO.getCode());
		parametre.setLibelle(parametreDTO.getLibelle());
		parametre.setNbre_question(parametreDTO.getNbre_question());
		parametre.setPonderation(parametreDTO.getPonderation());
		parametre.setActif(parametreDTO.getActif());

		return parametre;
	}
	
	public Question createQuestion(QuestionDTO questionDTO){
		if (questionDTO == null) 
			return null;
		Question question = new Question();
		question.setId(questionDTO.getId());
		question.setCode(questionDTO.getCode());
		question.setLibelle(questionDTO.getLibelle());
		question.setActif(questionDTO.getActif());
		if(questionDTO.getParametreDTO()!=null) 
			question.setParametre(createParametre(questionDTO.getParametreDTO()));

		return question;
	}
	
	public ReponseQualitative createReponseQualitative(ReponseQualitativeDTO reponseDTO){
		if (reponseDTO == null) 
			return null;
		ReponseQualitative reponse = new ReponseQualitative();
		reponse.setId(reponseDTO.getId());
		reponse.setCode(reponseDTO.getCode());
		reponse.setLibelle(reponseDTO.getLibelle());
		reponse.setActif(reponseDTO.getActif());
		reponse.setScore(reponseDTO.getScore());
		reponse.setQuestion(createQuestion(reponseDTO.getQuestionDTO()));

		return reponse;
	}
	
	
	public Ratio createRatio(RatioDTO ratioDTO){
		if (ratioDTO == null) 
			return null;
		Ratio ratio = new Ratio();
		ratio.setId(ratioDTO.getId());
		ratio.setCode(ratioDTO.getCode());
		ratio.setFormule(ratioDTO.getFormule());
		ratio.setPonderation(ratioDTO.getPonderation());
		ratio.setUnite(ratioDTO.getUnite());
		ratio.setActif(ratioDTO.getActif());

		return ratio;
	}

	public Calibrage createCalibrage(CalibrageDTO calibrageDTO){
		if (calibrageDTO == null) 
			return null;
		Calibrage calibrage = new Calibrage();
		calibrage.setId(calibrageDTO.getId());
		calibrage.setMin(calibrageDTO.getMin());
		calibrage.setMax(calibrageDTO.getMax());
		calibrage.setClasse(calibrageDTO.getClasse());
		calibrage.setActif(calibrageDTO.getActif());

		return calibrage;
	}
	
}