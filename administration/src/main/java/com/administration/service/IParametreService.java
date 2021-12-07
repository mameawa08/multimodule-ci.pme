package com.administration.service;

import java.util.List;

import com.administration.dto.ParametreDTO;
import com.administration.exception.ParametreException;
import com.administration.payload.ParametrePayload;

public interface IParametreService {

	List<ParametreDTO> getListeParametres();

	List<ParametreDTO> getListeParametresActifs();

	ParametreDTO createParametre(ParametrePayload parametrePayload) throws Exception;

	boolean deleteParametre(Long idParametre) throws Exception;

    ParametreDTO getParametre(Long id) throws ParametreException;
}
