package com.scoring.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.ParametreDTO;
import com.scoring.dto.QuestionDTO;
import com.scoring.dto.RepondantDTO;
import com.scoring.dto.ReponseQualitativeDTO;
import com.scoring.dto.Reponse_par_entreprise_DTO;
import com.scoring.models.Dirigeant;
import com.scoring.models.Entreprise;
import com.scoring.models.Parametre;
import com.scoring.models.Question;
import com.scoring.models.Repondant;
import com.scoring.models.ReponseQualitative;
import com.scoring.models.Reponse_par_entreprise;

@Named
public class DTOFactory {
	
	public EntrepriseDTO createEntreprise(Entreprise entreprise){
		if(entreprise == null)
			return null;
		EntrepriseDTO dto = new EntrepriseDTO();
		dto.setId(entreprise.getId());
		dto.setRaisonSociale(entreprise.getRaisonSociale());
		dto.setAnnee(entreprise.getAnnee());
		dto.setCapital(entreprise.getCapital());
		dto.setSecteur(entreprise.getSecteur());
		dto.setDescription(entreprise.getDescription());
		dto.setRegime(entreprise.getRegime());
		dto.setAdresse(entreprise.getAdresse());
		dto.setSiteWeb(entreprise.getSiteWeb());
		dto.setLogo(entreprise.getLogo());
		dto.setEligible(entreprise.isEligible());
		dto.setActif(entreprise.isActif());

		if(entreprise.getDirigeant() != null && entreprise.getDirigeant().getId() != null)
			dto.setDirigeant(createDirigeant(entreprise.getDirigeant()));

		dto.setFormeJur(entreprise.getFormeJur());

		return dto;
	}

	public List<EntrepriseDTO> createListEntreprise(List<Entreprise> entreprises){
		if(entreprises != null && entreprises.size() == 0)
			return new ArrayList<>();
		return entreprises.stream().map(this::createEntreprise).collect(Collectors.toList());
	}

	public DirigeantDTO createDirigeant(Dirigeant dirigeant) {
		if(dirigeant == null)
			return null;
		
		DirigeantDTO dto = new DirigeantDTO();
		dto.setId(dirigeant.getId());
		dto.setNom(dirigeant.getNom());
		dto.setPrenom(dirigeant.getPrenom());
		dto.setMobile(dirigeant.getMobile());
		dto.setNiveau(dirigeant.getNiveau());
		dto.setEmail(dirigeant.getEmail());
		dto.setSexe(dirigeant.getSexe());
		dto.setDate(dirigeant.getDate());
		dto.setLieu(dirigeant.getLieu());
		dto.setNationalite(dirigeant.getNationalite());
		dto.setAdresse(dirigeant.getAdresse());
		dto.setTypePiece(dirigeant.getTypePiece());
		dto.setNumeroCI(dirigeant.getNumeroCI());
		dto.setActif(dirigeant.isActif());

		if(dirigeant.getEntreprise() != null && dirigeant.getEntreprise().getId() != null)
			dto.setEntreprise(createEntreprise(dirigeant.getEntreprise()));

		return dto;
	}

	public List<DirigeantDTO> createListDirigeant(List<Dirigeant> dirigeants){
		if(dirigeants != null && dirigeants.size() == 0)
			return new ArrayList<>();
		return dirigeants.stream().map(this::createDirigeant).collect(Collectors.toList());
	}


	public RepondantDTO createRepondant(Repondant repondant){
		if(repondant == null)
			return null;
		
		RepondantDTO dto = new RepondantDTO();
		dto.setId(repondant.getId());
		dto.setNom(repondant.getNom());
		dto.setPrenom(repondant.getPrenom());
		dto.setMobile(repondant.getMobile());
		dto.setEmail(repondant.getEmail());
		dto.setFonction(repondant.getFonction());
		dto.setActif(repondant.isActif());

		dto.setEntreprise(createEntreprise(repondant.getEntreprise()));
		return dto;
	}

	public List<RepondantDTO> createListRepondant(List<Repondant> repondants){
		if(repondants != null && repondants.size() == 0)
			return new ArrayList<>();

		return repondants.stream().map(this::createRepondant).collect(Collectors.toList());
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
	
	public ParametreDTO createParametre(Parametre parametre){
		if (parametre == null)
			return null;
		ParametreDTO dto = new ParametreDTO();
		dto.setId(parametre.getId());
		dto.setCode(parametre.getCode());
		dto.setLibelle(parametre.getLibelle());
		dto.setNbre_question(parametre.getNbre_question());
		dto.setActif(parametre.getActif());

		return dto;
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
	
	public Reponse_par_entreprise_DTO createReponseParPME(Reponse_par_entreprise reponse){
		if (reponse == null)
			return null;
		Reponse_par_entreprise_DTO dto = new Reponse_par_entreprise_DTO();
		dto.setId(reponse.getId());
		dto.setReponse_eligibilite(reponse.isReponse_eligibilite());
		dto.setEntrepriseDTO(createEntreprise(reponse.getEntreprise()));
		dto.setQuestionDTO(createQuestion(reponse.getQuestion()));
		dto.setReponse_quali_DTO(createReponseQualitative(reponse.getReponse_quali()));
		return dto;
	}

	public List<Reponse_par_entreprise_DTO> createListReponseParPME(List<Reponse_par_entreprise> reponses){
		if(reponses == null || (reponses != null && reponses.size() == 0))
			return new ArrayList<>();

		return reponses.stream().map(this::createReponseParPME).collect(Collectors.toList());

	}
}
