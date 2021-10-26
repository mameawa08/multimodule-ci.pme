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

@Named("dtoFactory")
public class DTOFactory {
	
	public HabilitationDTO createHabilitation(Habilitation habilitation){
		if(habilitation == null)
			return null;
		HabilitationDTO dto = new HabilitationDTO();
		dto.setId(habilitation.getId());
		dto.setCode(habilitation.getCode());
		dto.setLibelle(habilitation.getLibelle());

		return dto;
	}

	public List<HabilitationDTO> createListHabilitation(List<Habilitation> habilitations){
		if(habilitations == null || (habilitations != null && habilitations.size() == 0))
			return new ArrayList<>();
		
		return habilitations.stream().map(this::createHabilitation).collect(Collectors.toList());
	}

	public ProfilDTO createProfil(Profil profil){
		if (profil == null) 
			return null;
		ProfilDTO dto = new ProfilDTO();
		dto.setId(profil.getId());
		dto.setLibelle(profil.getLibelle());
		dto.setCode(profil.getCode());
		dto.setActif(profil.getActif());
		dto.setHabilitations(createListHabilitation(profil.getHabilitations()));

		return dto;
	}

	public List<ProfilDTO> createListProfil(List<Profil> profils){
		if(profils == null || (profils != null && profils.size() == 0))
			return new ArrayList<>();
		
		return profils.stream().map(this::createProfil).collect(Collectors.toList());

	}
	
	public ParametreDTO createParametre(Parametre parametre){
		if (parametre == null) 
			return null;
		ParametreDTO dto = new ParametreDTO();
		dto.setId(parametre.getId());
		dto.setCode(parametre.getCode());
		dto.setLibelle(parametre.getLibelle());
		dto.setNbre_question(parametre.getNbre_question());
		dto.setPonderation(parametre.getPonderation());
		dto.setActif(parametre.getActif());

		return dto;
	}
	
	public List<ParametreDTO> createListParametre(List<Parametre> parametres){
		if(parametres == null || (parametres != null && parametres.size() == 0))
			return new ArrayList<>();
		
		return parametres.stream().map(this::createParametre).collect(Collectors.toList());

	}
	
	public QuestionDTO createQuestion(Question question){
		if (question == null) 
			return null;
		QuestionDTO dto = new QuestionDTO();
		dto.setId(question.getId());
		dto.setCode(question.getCode());
		dto.setLibelle(question.getLibelle());
		dto.setActif(question.getActif());
		if(question.getParametre()!=null) 
			dto.setParametreDTO(createParametre(question.getParametre()));

		return dto;
	}
	
	public List<QuestionDTO> createListQuestion(List<Question> questions){
		if(questions == null || (questions != null && questions.size() == 0))
			return new ArrayList<>();
		
		return questions.stream().map(this::createQuestion).collect(Collectors.toList());

	}
	
	
	public ReponseQualitativeDTO createReponseQualitative(ReponseQualitative reponse){
		if (reponse == null) 
			return null;
		ReponseQualitativeDTO dto = new ReponseQualitativeDTO();
		dto.setId(reponse.getId());
		dto.setCode(reponse.getCode());
		dto.setLibelle(reponse.getLibelle());
		dto.setActif(reponse.getActif());
		dto.setScore(reponse.getScore());
		dto.setQuestionDTO(createQuestion(reponse.getQuestion()));

		return dto;
	}
	
	public List<ReponseQualitativeDTO> createListReponseQualitative(List<ReponseQualitative> reponses){
		if(reponses == null || (reponses != null && reponses.size() == 0))
			return new ArrayList<>();
		
		return reponses.stream().map(this::createReponseQualitative).collect(Collectors.toList());

	}
	
	public RatioDTO createRatio(Ratio ratio){
		if (ratio == null) 
			return null;
		RatioDTO dto = new RatioDTO();
		dto.setId(ratio.getId());
		dto.setCode(ratio.getCode());
		dto.setFormule(ratio.getFormule());
		dto.setPonderation(ratio.getPonderation());
		dto.setUnite(ratio.getUnite());
		dto.setActif(ratio.getActif());

		return dto;
	}
	
	public List<RatioDTO> createListRatios(List<Ratio> ratios){
		if(ratios == null || (ratios != null && ratios.size() == 0))
			return new ArrayList<>();
		
		return ratios.stream().map(this::createRatio).collect(Collectors.toList());

	}
	
	
	
	public CalibrageDTO createCalibrage(Calibrage calibrage){
		if (calibrage == null) 
			return null;
		CalibrageDTO dto = new CalibrageDTO();
		dto.setId(calibrage.getId());
		dto.setMin(calibrage.getMin());
		dto.setMax(calibrage.getMax());
		dto.setClasse(calibrage.getClasse());
		dto.setActif(calibrage.getActif());

		return dto;
	}
	
	public List<CalibrageDTO> createListCalibrages(List<Calibrage> calibrages){
		if(calibrages == null || (calibrages != null && calibrages.size() == 0))
			return new ArrayList<>();
		
		return calibrages.stream().map(this::createCalibrage).collect(Collectors.toList());

	}


	
}