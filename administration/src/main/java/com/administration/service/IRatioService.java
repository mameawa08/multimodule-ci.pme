package com.administration.service;

import java.util.List;

import com.administration.dto.RatioDTO;
import com.administration.payload.RatioPayload;


public interface IRatioService {

	List<RatioDTO> getListeRatios();

	List<RatioDTO> getListeRatiosActif();

	List<RatioDTO> getListeRatiosByCode(String code);

	RatioDTO createRatio(RatioPayload ratioPayload) throws Exception;

	boolean deleteRatio(Long idRatio) throws Exception;

	

}
