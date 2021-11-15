package com.scoring.services;

import java.util.List;

import com.scoring.dto.IndicateurDTO;
import com.scoring.exceptions.IndicateurException;
import com.scoring.payloads.IndicateurPayload;

public interface IIndicateurService {
	
	public List<IndicateurDTO> getIndicateurs() throws IndicateurException;
	public IndicateurDTO getIndicateur(Long id) throws IndicateurException;
	public List<IndicateurDTO> getIndicateursByEntreprise(Long id) throws IndicateurException;
	public IndicateurDTO createIndicateur(IndicateurPayload payload) throws IndicateurException;
	public boolean switchStatus(Long id) throws IndicateurException;
}