package com.scoring.services;

import java.util.List;

import com.scoring.dto.RepondantDTO;
import com.scoring.exceptions.RepondantException;
import com.scoring.payloads.RepondantPayload;


public interface IRepondantService {
	
	public List<RepondantDTO> getRepondants() throws RepondantException;
	public RepondantDTO getRepondant(Long id) throws RepondantException;
	public RepondantDTO createRepondant(RepondantPayload payload) throws RepondantException;
	public boolean switchStatus(Long id) throws RepondantException;
}
