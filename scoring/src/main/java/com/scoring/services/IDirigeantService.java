package com.scoring.services;

import java.util.List;

import com.scoring.dto.DirigeantDTO;
import com.scoring.exceptions.DirigeantException;
import com.scoring.payloads.DirigeantPayload;


public interface IDirigeantService {
	
	public List<DirigeantDTO> getDirigeants() throws DirigeantException;
	public DirigeantDTO getDirigeant(Long id) throws DirigeantException;
	public DirigeantDTO createDirigeant(DirigeantPayload payload) throws DirigeantException;
	public boolean switchStatus(Long id) throws DirigeantException;
	DirigeantDTO getDirigeantByEntreprise(Long idEntreprise) throws DirigeantException;
}
