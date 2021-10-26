package com.administration.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.administration.dto.HabilitationDTO;
import com.administration.exception.HabilitationException;
import com.administration.mapping.DTOFactory;
import com.administration.model.Habilitation;
import com.administration.repository.HabilitationRepository;
import com.administration.service.IHabilitationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilitationServiceImpl implements IHabilitationService{
	
	@Autowired
	private HabilitationRepository habilitationRepository;

	@Autowired
	private DTOFactory dtoFactory;

	@Override
	public List<HabilitationDTO> getHabilitations() throws HabilitationException{
		List<Habilitation> list = habilitationRepository.findAll();
		List<HabilitationDTO> habilitations = new ArrayList<HabilitationDTO>();
		habilitations = dtoFactory.createListHabilitation(list);
		return habilitations;
	}
	
	@Override
	public HabilitationDTO getHabilitation(Long id) throws HabilitationException{
		Habilitation habilitation = habilitationRepository.findById(id).orElseThrow(() -> new HabilitationException("Habilitation :: "+id+" not found."));
		HabilitationDTO habilitationDTO = dtoFactory.createHabilitation(habilitation);
		return habilitationDTO;
	}
	

}
