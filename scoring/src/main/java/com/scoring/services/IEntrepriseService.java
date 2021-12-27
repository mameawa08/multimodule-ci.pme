package com.scoring.services;

import java.util.List;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.payloads.EntreprisePayload;

public interface IEntrepriseService {
	
	public List<EntrepriseDTO> getEntreprises() throws EntrepriseException;
	public EntrepriseDTO getEntreprise(Long id) throws EntrepriseException;
	public EntrepriseDTO createEntreprise(EntreprisePayload payload) throws EntrepriseException;
	public boolean switchStatus(Long id) throws EntrepriseException;
	List<EntrepriseDTO> getListEntreprisesAvecDemandeEnvoyee() throws EntrepriseException;
}
