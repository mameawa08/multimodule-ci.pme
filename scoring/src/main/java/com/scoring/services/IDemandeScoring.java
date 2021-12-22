package com.scoring.services;

import com.scoring.dto.DemandeScoringDTO;
import com.scoring.exceptions.DemandeException;
import com.scoring.payloads.DemandePayload;
import com.scoring.payloads.RapportPayload;

import java.util.List;

public interface IDemandeScoring {

    List<DemandeScoringDTO> getDemandes() throws DemandeException;

    DemandeScoringDTO getDemande(Long id) throws DemandeException;

    DemandeScoringDTO createDemande(Long idEntreprise) throws DemandeException;

    boolean envoyerDemande(Long id) throws DemandeException;

    boolean receptionnerDemande(Long id) throws DemandeException;

	boolean rejeterDemande(Long id, DemandePayload demandePayload) throws DemandeException;

	boolean validerDemandeProvisoire(Long id) throws DemandeException;

	boolean cloturerDemande(Long id) throws DemandeException;

	DemandeScoringDTO getDemandeBystatus(Long idEntreprise, int statutDemande) throws DemandeException;

	DemandeScoringDTO getDemandeNonClotureParEntreprise(Long idEntreprise) throws DemandeException;

    DemandeScoringDTO getDemandeOuverte(Long idEntreprise);
}
