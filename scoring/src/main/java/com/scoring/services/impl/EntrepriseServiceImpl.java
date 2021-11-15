package com.scoring.services.impl;

import java.util.List;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Entreprise;
import com.scoring.payloads.EntreprisePayload;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.services.IEntrepriseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private ModelFactory modelFactory;


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
		EntrepriseDTO entreprise = null;
		if(payload.getId() != null){
			entreprise = getEntreprise(payload.getId());
		}
		else{
			entreprise = new EntrepriseDTO();
			entreprise.setActif(true);
		}
		if(payload.getRaisonSociale() != null && payload.getRaisonSociale().equals(""))
			throw new EntrepriseException("La raison sociale est obligatoire.");

		if(payload.getSecteur() != null && payload.getSecteur().equals(""))
			throw new EntrepriseException("Le secteur est obligatoire.");

		if(payload.getDescription() != null && payload.getDescription().equals(""))
			throw new EntrepriseException("La description est obligatoire.");

		if(payload.getRegime() != null && payload.getRegime().equals(""))
			throw new EntrepriseException("Le regime est obligatoire.");

		if(payload.getAdresse() != null && payload.getAdresse().equals(""))
			throw new EntrepriseException("L'adresse est obligatoire.");

		if(payload.getSiteWeb() != null && payload.getSiteWeb().equals(""))
			throw new EntrepriseException("Le site web est obligatoire.");

		if(payload.getLogo() != null && payload.getLogo().equals(""))
			throw new EntrepriseException("Le logo est obligatoire.");

		if(payload.getFormeJur() != null && payload.getFormeJur().equals(""))
			throw new EntrepriseException("La forme juridique est obligatoire.");

		if(payload.getAnnee() == 0)
			throw new EntrepriseException("L'annee de creation est obligatoire.");

		if(payload.getCapital() != null && payload.getCapital().equals(0L))
			throw new EntrepriseException("Le capital est obligatoire.");

		entreprise = payloadToDTO.createEntreprise(payload);

		try {
			Entreprise model = modelFactory.createEntreprise(entreprise);
			entrepriseRepository.save(model);
			entreprise.setId(model.getId());
		}
		catch (Exception e){
			throw new EntrepriseException(e.getMessage(), e);
		}
		return entreprise;
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
