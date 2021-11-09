package com.scoring.services.impl;

import java.util.List;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.mapping.DTOFactory;
import com.scoring.models.Entreprise;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.repositories.EntrepriseRepository;
import com.scoring.services.IEntrepriseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private DTOFactory dtoFactory;


	@Override
	public List<EntrepriseDTO> getEntreprises() throws EntrepriseException{
		List<Entreprise> entreprises = entrepriseRepository.findAll();
		return dtoFactory.createListEntreprise(entreprises);
	}

	@Override
	public EntrepriseDTO getEntreprise(Long id) throws EntrepriseException{
		Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new EntrepriseException("Entreprise :: "+id+" not found."));
		return dtoFactory.createEntreprise(entreprise);
	}

	@Override
	public EntrepriseDTO createEntreprise(EntreprisePayload payload) throws EntrepriseException{
		return null;
	}

	@Override
	public boolean switchStatus(Long id) throws EntrepriseException{
		Entreprise entreprise = entrepriseRepository.findById(id).orElseThrow(() -> new EntrepriseException("Entreprise :: "+id+" not found."));
		try {
			entreprise.setActif(entreprise.isActif() ? false : true);
			entrepriseRepository.save(entreprise);
			return true;
		} catch (Exception e) {
			throw new EntrepriseException(e.getMessage(), e);
		}
	}

}
