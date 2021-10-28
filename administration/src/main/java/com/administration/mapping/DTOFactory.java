package com.administration.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.administration.dto.HabilitationDTO;
import com.administration.dto.ProfilDTO;
import com.administration.dto.UserDTO;
import com.administration.model.Habilitation;
import com.administration.model.Profil;
import com.administration.model.User;

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

	public List<UserDTO> createListUser(List<User> users) {
		if(users == null || (users != null && users.size() == 0))
			return new ArrayList<>();

		return users.stream().map(this::createUser).collect(Collectors.toList());
	}

	public UserDTO createUser(User user) {
		if(user == null)
			return null;
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setIdentifiant(user.getIdentifiant());
		dto.setEmail(user.getEmail());
		dto.setMotDePasse(user.getMotDePasse());
		dto.setMotDePassePrecedent(user.getMotDePassePrecedent());
		dto.setNom(user.getNom());
		dto.setPrenom(user.getPrenom());
		dto.setResetPasswordToken(user.getResetPasswordToken());
		dto.setActif(user.getActif());
		dto.setMdpModifie(user.getMdpModifie());
		dto.setProfil(createProfil(user.getProfil()));
		dto.setDateModificationMdp(user.getDateModificationMdp());
		dto.setConfirme(user.getConfirme());
		
		return dto;
	}

	
}