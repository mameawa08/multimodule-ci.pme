package com.scoring.services;

import java.util.List;

import com.scoring.dto.IndicateurDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.exceptions.IndicateurException;
import com.scoring.payloads.IndicateurPayload;

public interface IIndicateurService {
	
	public List<IndicateurDTO> getIndicateurs() throws IndicateurException;
	public IndicateurDTO getIndicateur(Long id) throws IndicateurException;
	public List<IndicateurDTO> getIndicateursByEntreprise(Long id) throws IndicateurException;
	public IndicateurDTO createIndicateur(IndicateurPayload payload) throws IndicateurException, DemandeException;
	public boolean switchStatus(Long id) throws IndicateurException;
	IndicateurDTO getLastIndicateur(Long idEntreprise) throws IndicateurException;

    IndicateurDTO getIndicateursByEntrepriseAndAnnee(Long id, int annee) throws IndicateurException;
}
