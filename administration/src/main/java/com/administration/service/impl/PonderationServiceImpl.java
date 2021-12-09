package com.administration.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administration.dto.ParametreDTO;
import com.administration.dto.Ponderation_scoreDTO;
import com.administration.exception.ParametreException;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.Parametre;
import com.administration.model.Ponderation_score;
import com.administration.payload.PonderationPayload;
import com.administration.repository.ParametreRepository;
import com.administration.repository.PonderationRepository;
import com.administration.service.IPonderationService;


@Service("ponderationService")
public class PonderationServiceImpl implements IPonderationService {
	
	@Autowired
	private PonderationRepository ponderationRepository;
	
	@Autowired
	private ParametreRepository parametreRepository;
	
	@Autowired
	private ParametreServiceImpl parametreServiceImpl;
	
	@Autowired
	private DTOFactory dtoFactory;
	
	@Autowired
	private ModelFactory modelFactory;

	@Override
	public List<Ponderation_scoreDTO> getListePonderations() throws ParametreException {
		List<Ponderation_score> ponderations = ponderationRepository.findAll();
		List<Ponderation_scoreDTO> ponderationsDto = new ArrayList<Ponderation_scoreDTO>();
		Ponderation_scoreDTO dto;
		for(Ponderation_score pon: ponderations){
			dto = dtoFactory.createPonderationScore(pon);
			if(pon.getParametre()!=null){
				ParametreDTO paramDTO = parametreServiceImpl.getParametre(pon.getParametre().getId());
				dto.setTypeScore(paramDTO.getLibelle());
			}
			ponderationsDto.add(dto);
		}		 
		return ponderationsDto;
	}
	
	@Override
	public List<Ponderation_scoreDTO> getListePonderationsActif() {
		List<Ponderation_score> ponderations = ponderationRepository.findAllActif();
		List<Ponderation_scoreDTO> ponderationsDto = dtoFactory.createListPonderations(ponderations);
		return ponderationsDto;
	}
	
	@Override
	public Ponderation_scoreDTO getPonderationsByParametre(Long idParametre) {
		Ponderation_score ponderation = ponderationRepository.findPonderationByParametre(idParametre);
		Ponderation_scoreDTO ponderationDto = dtoFactory.createPonderationScore(ponderation);
		return ponderationDto;
	}
	
	@Override
	public Ponderation_scoreDTO getPonderationScoreFinancier() {
		Ponderation_score ponderation = ponderationRepository.findPonderationScoreFinancier();
		Ponderation_scoreDTO ponderationDto = dtoFactory.createPonderationScore(ponderation);
		return ponderationDto;
	}
	
	
	@Override
	public Ponderation_scoreDTO createPonderation(PonderationPayload ponderationPayload) throws Exception {
		
		if (ponderationPayload.getIdParametre()==null && ponderationPayload.getTypeScore()!=null
				&& ponderationPayload.getTypeScore().equals("")) {
			throw new Exception("Veuillez choisir un paramètre si vous souhaitez enregistrer un score lié à un paramètre qualitatif !");
		}
		if (ponderationPayload.getPonderation()==null) {
			throw new Exception("La valeur de la pondération est obligatoire !");
		}
		List<Ponderation_scoreDTO> listPon = getListePonderations();
		Long nbre=0L;
		for(Ponderation_scoreDTO pon : listPon){
			if(!ponderationPayload.getId().equals(pon.getId()))
			nbre+=pon.getPonderation();
		}
		nbre+=ponderationPayload.getPonderation();
		if(nbre>100L){
			throw new Exception("La somme des pondérations doit être inférieure à 100 !");
		}
		Ponderation_scoreDTO ponderationDTO = new Ponderation_scoreDTO();
		ponderationDTO.setId(ponderationPayload.getId());
		ponderationDTO.setTypeScore(ponderationPayload.getTypeScore());
		ponderationDTO.setPonderation(ponderationPayload.getPonderation());
		Parametre parametre = null;
		if(ponderationPayload.getIdParametre()!=null) 
			parametre = parametreRepository.findById(ponderationPayload.getIdParametre()).orElseThrow(() -> new Exception("Not found."));
		ponderationDTO.setParametreDTO(dtoFactory.createParametre(parametre));
		ponderationDTO.setActif(1);
		
		Ponderation_score ponderation = modelFactory.createPonderationScore(ponderationDTO);
		ponderation = ponderationRepository.save(ponderation);
		return ponderationDTO;
		
	}
	
	@Override
	public boolean deletePonderation(Long idPonderation) throws Exception {
		try {
			if (idPonderation == null) {
				throw new Exception("La pondération à supprimer est nulle !");
			}
			Ponderation_score p = ponderationRepository.findById(idPonderation).orElseThrow(()-> new Exception("Not found."));
			if (p != null)
				ponderationRepository.delete(p);
			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}
