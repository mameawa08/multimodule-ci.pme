package com.administration.service;

import java.util.List;

import com.administration.dto.FormeJuridiqueDTO;
import com.administration.dto.SecteurActiviteDTO;
import com.administration.exception.ReferentielException;

public interface IReferentielService {

	List<SecteurActiviteDTO> getListeSecteurs();

	List<FormeJuridiqueDTO> getListeFormesJuridique();

	SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException;

	FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException;


}
