package com.scoring.services;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.exceptions.DemandeException;

import java.util.List;

public interface IDemandeScoring {

    List<DemandeScoringDTO> getDemandes() throws DemandeException;

    DemandeScoringDTO getDemande(Long id) throws DemandeException;

    DemandeScoringDTO createDemande(Long idEntreprise) throws DemandeException;

    boolean envoyerDemande(Long id) throws DemandeException;

    boolean receptionnerDemande(Long id) throws DemandeException;
}
