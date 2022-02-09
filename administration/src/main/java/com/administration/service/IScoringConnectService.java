package com.administration.service;

import com.administration.dto.DemandeAccompagnementDTO;
import com.administration.dto.DemandeScoringDTO;
import com.administration.exception.ScoringConnectException;

public interface IScoringConnectService {

    public Long getEntreprise(Long id) throws ScoringConnectException;

    public DemandeScoringDTO getUserDemande(Long idUser) ;

    DemandeAccompagnementDTO getDemandeAccompagnement(Long idDemandeScoring);
}
