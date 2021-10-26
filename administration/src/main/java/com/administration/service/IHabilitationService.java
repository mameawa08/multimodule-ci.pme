package com.administration.service;

import java.util.List;

import com.administration.dto.HabilitationDTO;
import com.administration.exception.HabilitationException;

public interface IHabilitationService {
	
	public List<HabilitationDTO> getHabilitations() throws HabilitationException;
	
	public HabilitationDTO getHabilitation(Long id) throws HabilitationException;
	
	// public HabilitationDTO ajouterHabilitation(HabilitationDTO profil) throws HabilitationException;

	// public boolean changerStatusHabilitation(Long id) throws HabilitationException;
	
}
