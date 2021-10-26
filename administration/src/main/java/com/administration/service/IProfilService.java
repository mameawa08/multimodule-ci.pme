package com.administration.service;

import java.util.List;

import com.administration.dto.ProfilDTO;
import com.administration.exception.ProfilException;
import com.administration.payload.ProfilPayload;

public interface IProfilService {
	
	public List<ProfilDTO> getProfils() throws ProfilException;
	
	public ProfilDTO getProfil(Long id) throws ProfilException;
	
	public ProfilDTO ajouterProfil(ProfilPayload profil) throws ProfilException;

	public boolean changerStatusProfil(Long id) throws ProfilException;
	
}
