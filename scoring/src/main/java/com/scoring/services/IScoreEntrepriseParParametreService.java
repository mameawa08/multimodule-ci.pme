package com.scoring.services;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;

import java.util.List;

public interface IScoreEntrepriseParParametreService {

    public List<ScoreEntrepriseParParametreDTO> getScoreEntrepriseParParametre(Long entrepriseId) throws ScoreEntrepriseParParametreException;
    public ScoreEntrepriseParParametreDTO getScoreEntrepriseParParametre(Long entrepriseId, Long parametreId) throws ScoreEntrepriseParParametreException;
}
