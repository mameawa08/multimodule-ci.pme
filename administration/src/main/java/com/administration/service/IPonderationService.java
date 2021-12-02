package com.administration.service;

import java.util.List;

import com.administration.dto.Ponderation_scoreDTO;
import com.administration.payload.PonderationPayload;


public interface IPonderationService {

	List<Ponderation_scoreDTO> getListePonderations();

	List<Ponderation_scoreDTO> getListePonderationsActif();

	Ponderation_scoreDTO getPonderationsByParametre(Long idParametre);

	Ponderation_scoreDTO createPonderation(PonderationPayload ponderationPayload) throws Exception;

	boolean deletePonderation(Long idPonderation) throws Exception;

	Ponderation_scoreDTO getPonderationScoreFinancier();

	

}
