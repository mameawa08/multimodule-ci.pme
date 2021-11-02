package com.administration.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.administration.dto.HabilitationDTO;
import com.administration.dto.ProfilDTO;
import com.administration.exception.HabilitationException;
import com.administration.exception.ProfilException;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Profil;
import com.administration.payload.ProfilPayload;
import com.administration.repository.ProfilRepository;
import com.administration.service.IHabilitationService;
import com.administration.service.IProfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilServiceImpl implements IProfilService{
	
	@Autowired
	private ProfilRepository profilRepository;

	@Autowired
	private IHabilitationService habilitationService;

	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<ProfilDTO> getProfils() throws ProfilException{
		List<ProfilDTO> profils = new ArrayList<ProfilDTO>();
		List<Profil> list = profilRepository.findAll();
		profils = dtoFactory.createListProfil(list);
		return profils;
	}
	
	@Override
	public ProfilDTO getProfil(Long id) throws ProfilException{
		Profil p = profilRepository.findById(id).orElseThrow(() -> new ProfilException("Profil :: "+id+" not found."));
		ProfilDTO profil = dtoFactory.createProfil(p);
		return profil;
	}
	
	@Override
	public ProfilDTO ajouterProfil(ProfilPayload profil) throws ProfilException{
		ProfilDTO profilDTO;
		if(profil == null)
			throw new ProfilException("Profil :: aucune donnee a ajouter.");
		if(profil.getId() != null)
			profilDTO = getProfil(profil.getId());
		else
			profilDTO = new ProfilDTO();

		if(profil != null && (profil.getCode() != null && profil.getCode().equals(""))) throw new ProfilException("Le code est obligatoire.");
		if(profil != null && (profil.getLibelle() != null && profil.getLibelle().equals(""))) throw new ProfilException("Le libelle est obligatoire.");
//		if(profil != null && (profil.getHabilitations() != null && profil.getHabilitations().size() == 0)) throw new ProfilException("Le profil doit avoir une ahbilitation.");

		profilDTO.setActif(1);
		profilDTO.setCode(profil.getCode());
		profilDTO.setLibelle(profil.getLibelle());

//		if (profil.getId() != null){
//
//		}

		List<HabilitationDTO> habilitations = new ArrayList<HabilitationDTO>();
		if(profil.getHabilitations() != null && profil.getHabilitations().size() > 0){
			for (Integer h : profil.getHabilitations()) {
				try {
					HabilitationDTO habilitation = habilitationService.getHabilitation(Long.valueOf(h));
					habilitations.add(habilitation);
				} catch (HabilitationException e) {
					throw new ProfilException("Profil :: "+e.getMessage(), e);
				}
			}

			profilDTO.setHabilitations(habilitations);
		}



		Profil p = modelFactory.createProfil(profilDTO);
		try {
			profilRepository.save(p);
			profilDTO.setId(p.getId());
		} catch (Exception e) {
			throw new ProfilException("Profil :: "+e.getMessage());
		}

		return profilDTO;
	}

	@Override
	public boolean changerStatusProfil(Long id) throws ProfilException{
		Profil profil = profilRepository.findById(id).orElseThrow(() -> new ProfilException("Profil :: "+id+" not found."));
		profil.setActif(profil.getActif() == 1 ? 0 : 1);
		try {
			profilRepository.save(profil);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
