package com.administration.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.administration.dto.CalibrageDTO;
import com.administration.dto.FormeJuridiqueDTO;
import com.administration.dto.HabilitationDTO;
import com.administration.dto.ParametreDTO;
import com.administration.dto.Ponderation_scoreDTO;
import com.administration.dto.ProfilDTO;
import com.administration.dto.QuestionDTO;
import com.administration.dto.RatioDTO;
import com.administration.dto.ReponseQualitativeDTO;
import com.administration.dto.SecteurActiviteDTO;
import com.administration.dto.ThemeDTO;
import com.administration.dto.UserDTO;
import com.administration.model.Calibrage;
import com.administration.model.FormeJuridique;
import com.administration.model.Habilitation;
import com.administration.model.Parametre;
import com.administration.model.Ponderation_score;
import com.administration.model.Profil;
import com.administration.model.Question;
import com.administration.model.Ratio;
import com.administration.model.ReponseQualitative;
import com.administration.model.SecteurActivite;
import com.administration.model.Theme;
import com.administration.model.User;
import com.administration.payload.UserPaylaod;


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
	
	public List<UserDTO> createListUser(List<User> users){
		if(users == null || (users != null && users.size() == 0))
			return new ArrayList<>();
		
		return users.stream().map(this::createUser).collect(Collectors.toList());

	}

	public UserDTO createUser(User user){
		if (user == null) 
			return null;
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setNom(user.getNom());
		dto.setPrenom(user.getPrenom());
		dto.setActif(user.getActif());
		dto.setMdpModifie(user.getMdpModifie());
		dto.setMotDePassePrecedent(user.getMotDePassePrecedent());
		dto.setDateModificationMdp(user.getDateModificationMdp());
		dto.setResetPasswordToken(user.getResetPasswordToken());
		dto.setConfirme(user.getConfirme());
		dto.setProfil(createProfil(user.getProfil()));

		dto.setConfirmationToken(user.getConfirmationToken());
		dto.setFonction(user.getFonction());
		dto.setEntrepriseLibelle(user.getEntrepriseLibelle());
		dto.setMobile(user.getMobile());

		dto.setEntrepriseId(user.getEntrepriseId());


		return dto;
	}
	/**
	 * Don't forget to set the profil after the call of this methode
	 * 
	 * @param user
	 * @return UserDTO
	 */
	public UserDTO createUser(UserPaylaod user){
		if (user == null) 
			return null;
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setNom(user.getNom());
		dto.setPrenom(user.getPrenom());
		//Don't forget to set the profil after the call of this methode

		return dto;
	}

	public UserDTO createUser(UserPaylaod user, UserDTO userDTO){
		if (user == null)
			return userDTO;
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		if(user.getPassword() != null && !user.getPassword().equals("")){
			userDTO.setPassword(user.getPassword());
		}
		userDTO.setNom(user.getNom());
		userDTO.setPrenom(user.getPrenom());
		//Don't forget to set the profil after the call of this methode

		return userDTO;
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
		dto.setCompteur(parametre.getCompteur());

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
		dto.setCompteur(question.getCompteur());
		
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
		dto.setCompteur(reponse.getCompteur());

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
		dto.setLibelle(ratio.getLibelle());
		dto.setFormule(ratio.getFormule());
		dto.setPonderation(ratio.getPonderation());
		dto.setUnite(ratio.getUnite());
		dto.setActif(ratio.getActif());
		dto.setCompteur(ratio.getCompteur());

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
		dto.setRatioDTO(createRatio(calibrage.getRatio()));

		return dto;
	}

	public List<CalibrageDTO> createListCalibrages(List<Calibrage> calibrages){
		if(calibrages == null || (calibrages != null && calibrages.size() == 0))
			return new ArrayList<>();

		return calibrages.stream().map(this::createCalibrage).collect(Collectors.toList());

	}
	
	
	public Ponderation_scoreDTO createPonderationScore(Ponderation_score ponderation){
		if (ponderation == null)
			return null;
		Ponderation_scoreDTO dto = new Ponderation_scoreDTO();
		dto.setId(ponderation.getId());
		dto.setTypeScore(ponderation.getTypeScore());
		dto.setPonderation(ponderation.getPonderation());
		dto.setParametreDTO(createParametre(ponderation.getParametre()));
		dto.setActif(ponderation.getActif());
		dto.setCompteur(ponderation.getCompteur());

		return dto;
	}
	
	public List<Ponderation_scoreDTO> createListPonderations(List<Ponderation_score> ponderations){
		if(ponderations == null || (ponderations != null && ponderations.size() == 0))
			return new ArrayList<>();

		return ponderations.stream().map(this::createPonderationScore).collect(Collectors.toList());

	}
	
	public FormeJuridiqueDTO createFormeJuridique(FormeJuridique formeJuridique){
		if (formeJuridique == null)
			return null;
		FormeJuridiqueDTO dto = new FormeJuridiqueDTO();
		dto.setId(formeJuridique.getId());
		dto.setCode(formeJuridique.getCode());
		dto.setLibelle(formeJuridique.getLibelle());
		dto.setActif(formeJuridique.getActif());

		return dto;
	}
	
	public List<FormeJuridiqueDTO> createListFormeJuridiques(List<FormeJuridique> formeJuridiques){
		if(formeJuridiques == null || (formeJuridiques != null && formeJuridiques.size() == 0))
			return new ArrayList<>();

		return formeJuridiques.stream().map(this::createFormeJuridique).collect(Collectors.toList());

	}

	public SecteurActiviteDTO createSecteurActivite(SecteurActivite secteurActivite){
		if (secteurActivite == null)
			return null;
		SecteurActiviteDTO dto = new SecteurActiviteDTO();
		dto.setId(secteurActivite.getId());
		dto.setLibelle(secteurActivite.getLibelle());
		dto.setActif(secteurActivite.getActif());

		return dto;
	}

	public List<SecteurActiviteDTO> createListSecteurActivites(List<SecteurActivite> secteurs){
		if(secteurs == null || (secteurs != null && secteurs.size() == 0))
			return new ArrayList<>();

		return secteurs.stream().map(this::createSecteurActivite).collect(Collectors.toList());

	}
	
	public ThemeDTO createTheme(Theme theme){
		if (theme == null)
			return null;
		ThemeDTO dto = new ThemeDTO();
		dto.setId(theme.getId());
		dto.setCode(theme.getCode());
		dto.setLibelle(theme.getLibelle());
		
		return dto;
	}
	
	public List<ThemeDTO> createListTheme(List<Theme> listTheme){
		if(listTheme == null || (listTheme != null && listTheme.size() == 0))
			return new ArrayList<>();

		return listTheme.stream().map(this::createTheme).collect(Collectors.toList());

	}
	
}