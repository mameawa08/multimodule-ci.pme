package com.scoring.services.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.scoring.dto.DirigeantDTO;
import com.scoring.dto.EntrepriseDTO;
import com.scoring.exceptions.DirigeantException;
import com.scoring.exceptions.EntrepriseException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.mapping.PayloadToDTO;
import com.scoring.models.Dirigeant;
import com.scoring.payloads.DirigeantPayload;
import com.scoring.repository.DirigeantRepository;
import com.scoring.services.IDirigeantService;

import com.scoring.services.IEntrepriseService;
import com.scoring.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirigeantServiceImpl implements IDirigeantService {

	@Autowired
	private DirigeantRepository dirigeantRepository;
	
	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private PayloadToDTO payloadToDTO;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	private IEntrepriseService entrepriseService;

	public final static String REGEX_EMAIL = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";


	@Override
	public List<DirigeantDTO> getDirigeants() throws DirigeantException{
		List<Dirigeant> dirigeants = dirigeantRepository.findAll();
		return dtoFactory.createListDirigeant(dirigeants);
	}

	@Override
	public DirigeantDTO getDirigeant(Long id) throws DirigeantException{
		Dirigeant dirigeant = dirigeantRepository.findById(id).orElseThrow(() -> new DirigeantException("Dirigeant :: "+id+" not found."));
		return dtoFactory.createDirigeant(dirigeant);
	}
	
	@Override
	public DirigeantDTO getDirigeantByEntreprise(Long idEntreprise) throws DirigeantException{
		Dirigeant dirigeant = dirigeantRepository.findDirigeantByEntreprise(idEntreprise);
		return dtoFactory.createDirigeant(dirigeant);
	}

	@Override
	public DirigeantDTO createDirigeant(DirigeantPayload payload) throws DirigeantException{
		DirigeantDTO dirigeant = null;
		if(payload.getId() != null){
			dirigeant = getDirigeant(payload.getId());
		}
		else{
			dirigeant = new DirigeantDTO();
		}
		if(payload.getNom() != null && payload.getNom().equals(""))
			throw new DirigeantException("Le nom est obligatoire.");

		if(payload.getPrenom() != null && payload.getPrenom().equals(""))
			throw new DirigeantException("Le prenom est obligatoire.");

		if(payload.getMobile() != null && payload.getMobile().equals(""))
			throw new DirigeantException("Le numero de telephone mobile est obligatoire.");

		if(payload.getEmail() != null && payload.getEmail().equals(""))
			throw new DirigeantException("L'email est obligatoire.");

		if(!Pattern.compile(REGEX_EMAIL).matcher(payload.getEmail()).matches())
			throw new DirigeantException("Le format de l'email est incorrect.");

		if(payload.getSexe() != null && payload.getSexe().equals(""))
			throw new DirigeantException("Le sexe est obligatoire.");

		if(payload.getDate() != null && payload.getDate().equals(""))
			throw new DirigeantException("La date de naissance est obligatoire.");

		if(payload.getNationalite() != null && payload.getNationalite().equals(""))
			throw new DirigeantException("La nationalité est obligatoire.");

		if(payload.getAdresse() != null && payload.getAdresse().equals(""))
			throw new DirigeantException("L'adresse est obligatoire.");

		if(payload.getTypePiece() != null && payload.getTypePiece().equals(""))
			throw new DirigeantException("Le type de piece d'identification est obligatoire.");

		if(payload.getNumeroCI() != null && payload.getNumeroCI().equals(""))
			throw new DirigeantException("Le numero didentification est obligatoire.");


		if(payload.getEntreprise() == 0)
			throw new DirigeantException("L'entreprise est obligatoire.");

		if (DateUtils.getAge(DateUtils.parseDate(payload.getDate())) < 18)
			throw new DirigeantException("Le dirigeant doit avoir au moins 18 ans.");

		dirigeant = payloadToDTO.createDirigeant(payload);
		dirigeant.setActif(true);

		try {
			EntrepriseDTO entreprise = entrepriseService.getEntreprise((long)payload.getEntreprise());
			dirigeant.setEntreprise(entreprise);
		} catch (EntrepriseException e) {
			throw new DirigeantException(e.getMessage(), e);
		}

		Dirigeant model = modelFactory.createDirigeant(dirigeant);

		try {
			dirigeantRepository.save(model);
			dirigeant.setId(model.getId());
		} catch (Exception e) {
			throw new DirigeantException(e.getMessage(), e);
		}

		return dirigeant;
	}

	@Override
	public boolean switchStatus(Long id) throws DirigeantException{
		Dirigeant dirigeant = dirigeantRepository.findById(id).orElseThrow(() -> new DirigeantException("Dirigeant :: "+id+" not found."));
		try {
			dirigeant.setActif(dirigeant.isActif() ? false : true);
			dirigeantRepository.save(dirigeant);
			return true;
		} catch (Exception e) {
			throw new DirigeantException(e.getMessage(), e);
		}
	}

}
