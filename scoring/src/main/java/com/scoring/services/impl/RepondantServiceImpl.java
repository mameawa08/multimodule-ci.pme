package com.scoring.services.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.scoring.dto.EntrepriseDTO;
import com.scoring.dto.RepondantDTO;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.exceptions.RepondantException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Repondant;
import com.scoring.payloads.RepondantPayload;
import com.scoring.repository.RepondantRepository;
import com.scoring.services.IEntrepriseService;
import com.scoring.services.IRepondantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepondantServiceImpl implements IRepondantService {

	@Autowired
	private RepondantRepository repondantRepository;
	
	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private IEntrepriseService entrepriseService;

	@Autowired
	private ModelFactory modelFactory;

	public final static String REGEX_EMAIL = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

	@Override
	public List<RepondantDTO> getRepondants() throws RepondantException{
		List<Repondant> repondants = repondantRepository.findAll();
		return dtoFactory.createListRepondant(repondants);
	}

	@Override
	public RepondantDTO getRepondant(Long id) throws RepondantException{
		Repondant repondant = repondantRepository.findById(id).orElseThrow(() -> new RepondantException("Repondant :: "+id+" not found."));
		return dtoFactory.createRepondant(repondant);
	}

	@Override
	public RepondantDTO createRepondant(RepondantPayload payload) throws RepondantException{
		RepondantDTO repondant = null;
		if(payload.getId() != null){
			repondant = getRepondant(payload.getId());
		}
		else{
			repondant = new RepondantDTO();
		}
		if(payload.getNom() != null && payload.getNom().equals(""))
			throw new RepondantException("Le nom est obligatoire.");
			
		if(payload.getPrenom() != null && payload.getPrenom().equals(""))
			throw new RepondantException("Le prenom est obligatoire.");
		
		if(payload.getMobile() != null && payload.getMobile().equals(""))
			throw new RepondantException("Le numero de telephone mobile est obligatoire.");
		
		if(payload.getEmail() != null && payload.getEmail().equals(""))
			throw new RepondantException("L'email est obligatoire.");
	
		if(!Pattern.compile(REGEX_EMAIL).matcher(payload.getEmail()).matches())
			throw new RepondantException("Le format de l'email est incorrect.");
	
		if(payload.getFonction() != null && payload.getFonction().equals(""))
			throw new RepondantException("Le nom est obligatoire.");
		
		if(payload.getEntreprise() == 0)
			throw new RepondantException("L'entreprise est obligatoire.");

		repondant = payloadToDTO.createRepondant(payload);
		repondant.setActif(true);

		try {
			EntrepriseDTO entreprise = entrepriseService.getEntreprise((long)payload.getEntreprise());
			repondant.setEntreprise(entreprise);
		} catch (EntrepriseException e) {
			throw new RepondantException(e.getMessage(), e);
		}
		
		Repondant model = modelFactory.createRepondant(repondant);
		
		try {
			repondantRepository.save(model);
			repondant.setId(model.getId());
		} catch (Exception e) {
			throw new RepondantException(e.getMessage(), e);
		}

		return repondant;
	}

	@Override
	public boolean switchStatus(Long id) throws RepondantException{
		Repondant repondant = repondantRepository.findById(id).orElseThrow(() -> new RepondantException("Repondant :: "+id+" not found."));
		try {
			repondant.setActif(repondant.isActif() ? false : true);
			repondantRepository.save(repondant);
			return true;
		} catch (Exception e) {
			throw new RepondantException(e.getMessage(), e);
		}
	}

}
