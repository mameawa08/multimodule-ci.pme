package com.administration.service.impl;

import java.util.List;

import com.administration.exception.ReferentielException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.FormeJuridiqueDTO;
import com.administration.dto.ParametreDTO;
import com.administration.dto.SecteurActiviteDTO;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.FormeJuridique;
import com.administration.model.Parametre;
import com.administration.model.SecteurActivite;
import com.administration.payload.ParametrePayload;
import com.administration.repository.FormeJuridiqueRepository;
import com.administration.repository.ParametreRepository;
import com.administration.repository.SecteurActiviteRepository;
import com.administration.service.IParametreService;
import com.administration.service.IReferentielService;




@Service("referentielService")
public class ReferentielServiceImpl implements IReferentielService {
	
	@Autowired
	private SecteurActiviteRepository secteurRepository;
	
	@Autowired
	private FormeJuridiqueRepository formeJurRepository;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<SecteurActiviteDTO> getListeSecteurs() {
		List<SecteurActivite> secteurs = secteurRepository.findAll();
		List<SecteurActiviteDTO> secteursDto = dtoFactory.createListSecteurActivites(secteurs);
		return secteursDto;
	}
	
	@Override
	public List<FormeJuridiqueDTO> getListeFormesJuridique() {
		List<FormeJuridique> formesJur = formeJurRepository.findAll();
		List<FormeJuridiqueDTO> formesJursDto = dtoFactory.createListFormeJuridiques(formesJur);
		return formesJursDto;
	}

	@Override
	public SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException{
		SecteurActivite secteur = secteurRepository.findById(id).orElseThrow(() -> new ReferentielException("Secteur activite :: not found."));
		return dtoFactory.createSecteurActivite(secteur);
	}

	@Override
	public FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException {
		FormeJuridique forme = formeJurRepository.findById(id).orElseThrow(() -> new ReferentielException("Forme juridique :: not found."));
		return dtoFactory.createFormeJuridique(forme);
	}


}
