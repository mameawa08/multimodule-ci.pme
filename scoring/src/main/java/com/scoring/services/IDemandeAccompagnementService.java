package com.scoring.services;

import com.scoring.dto.DemandeAccompagnementDTO;
import com.scoring.exceptions.DemandeAccompagnementException;

import java.util.List;

public interface IDemandeAccompagnementService {

    DemandeAccompagnementDTO getDemandeAccompagnementByDemandeScoring(Long demandeScoring);

    List<DemandeAccompagnementDTO> getDemandeAccompagnementByEntreprise(Long entreprise);

    DemandeAccompagnementDTO createDemandeAccompagnement(Long idDemandeScoring) throws DemandeAccompagnementException;

    DemandeAccompagnementDTO envoyerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException;

    DemandeAccompagnementDTO receptionnerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException;

    boolean annulerDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException;

    boolean closeDemandeAccompagnement(Long idDemandeAccompagnement) throws DemandeAccompagnementException;
}
