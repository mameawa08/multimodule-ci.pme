package com.scoring.services;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;

import java.util.List;

public interface IScoreEntrepriseParParametreService {

   
   
    ScoreEntrepriseParParametreDTO saveScore(ScoreEntrepriseParParametreDTO score) throws ScoreEntrepriseParParametreException;
	ScoreEntrepriseParParametreDTO getScoreDemandeParParametreOrNull(Long demandeId, Long parametreId)
			throws ScoreEntrepriseParParametreException;
	List<ScoreEntrepriseParParametreDTO> getScoreDemandeParParametre(Long idDemande)
			throws ScoreEntrepriseParParametreException;
	ScoreEntrepriseParParametreDTO getScoreDemandeParParametre(Long idDemande, Long parametreId)
			throws ScoreEntrepriseParParametreException;
}
