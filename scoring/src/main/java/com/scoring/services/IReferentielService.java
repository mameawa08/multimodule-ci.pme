package com.scoring.services;

import com.scoring.dto.FormeJuridiqueDTO;
import com.scoring.dto.SecteurActiviteDTO;
import com.scoring.exceptions.ReferentielException;

public interface IReferentielService {

    public FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException;

    public SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException;
}
