package com.scoring.services.impl;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.Entreprise;
import com.scoring.models.ScoreEntrepriseParParametre;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.ParametreRepository;
import com.scoring.repository.ScoreEntrepriseParParametreRepository;
import com.scoring.services.IScoreEntrepriseParParametreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScoreEntrepriseParParametreServiceImpl implements IScoreEntrepriseParParametreService {

    @Autowired
    private ScoreEntrepriseParParametreRepository scoreEntrepriseParParametreRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private ModelFactory modelFactory;

    @Override
    public List<ScoreEntrepriseParParametreDTO> getScoreEntrepriseParParametre(Long entrepriseId) throws ScoreEntrepriseParParametreException {
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: entreprise "+entrepriseId+" not found."));
        List<ScoreEntrepriseParParametre> scores = scoreEntrepriseParParametreRepository.findByEntreprise(entreprise);

        return dtoFactory.createListScoreEntrepriseParParametre(scores);
    }

    @Override
    public ScoreEntrepriseParParametreDTO getScoreEntrepriseParParametre(Long entrepriseId, Long parametreId) throws ScoreEntrepriseParParametreException {
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: entreprise "+entrepriseId+" not found."));
        Parametre parametre = parametreRepository.findById(parametreId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: parametre "+parametreId+" not found."));
        ScoreEntrepriseParParametre score = scoreEntrepriseParParametreRepository.findByEntrepriseAndParametre(entreprise, parametre).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: not found."));;

        return dtoFactory.createScoreEntrepriseParParametre(score);
    }

    public ScoreEntrepriseParParametreDTO saveScore(ScoreEntrepriseParParametreDTO score) throws ScoreEntrepriseParParametreException{
        if (score.getEntreprise() == null || (score.getEntreprise() != null && score.getEntreprise().getId() == null))
            throw new ScoreEntrepriseParParametreException("L'entreprise est obligatoire.");

        if (score.getParametre() == null || (score.getParametre() != null && score.getParametre().getId() == null))
            throw new ScoreEntrepriseParParametreException("Le parametre est obligatoire.");

        //contraintes sur le score

        ScoreEntrepriseParParametre model = modelFactory.createScoreEntrepriseParParametre(score);

        return null;
    }
}
