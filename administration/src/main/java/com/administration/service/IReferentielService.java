package com.administration.service;

import java.util.List;

import com.administration.dto.FormeJuridiqueDTO;
import com.administration.dto.SecteurActiviteDTO;

public interface IReferentielService {

	List<SecteurActiviteDTO> getListeSecteurs();

	List<FormeJuridiqueDTO> getListeFormesJuridique();


}
