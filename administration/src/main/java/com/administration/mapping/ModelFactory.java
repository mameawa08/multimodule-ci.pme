package com.administration.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.administration.dto.HabilitationDTO;
import com.administration.dto.ProfilDTO;
import com.administration.model.Habilitation;
import com.administration.model.Profil;

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

	
}