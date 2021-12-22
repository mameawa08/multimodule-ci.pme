package com.scoring.services.impl;

import com.scoring.dto.ScoreEntrepriseParParametreDTO;
import com.scoring.exceptions.ScoreEntrepriseParParametreException;
import com.scoring.mapping.DTOFactory;
import com.scoring.mapping.ModelFactory;
import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Parametre;
import com.scoring.models.ScoreEntrepriseParParametre;
import com.scoring.repository.DemandeScoringRepository;
import com.scoring.repository.EntrepriseRepository;
import com.scoring.repository.ParametreRepository;
import com.scoring.repository.ScoreEntrepriseParParametreRepository;
import com.scoring.services.IScoreEntrepriseParParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreEntrepriseParParametreServiceImpl implements IScoreEntrepriseParParametreService {

    @Autowired
    private ScoreEntrepriseParParametreRepository scoreEntrepriseParParametreRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private ParametreRepository parametreRepository;
    
    @Autowired
    private DemandeScoringRepository demandeScoringRepository;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private ModelFactory modelFactory;

    @Override
    public List<ScoreEntrepriseParParametreDTO> getScoreDemandeParParametre(Long idDemande) throws ScoreEntrepriseParParametreException {
       // Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: entreprise "+entrepriseId+" not found."));
        DemandeScoring demande = demandeScoringRepository.findById(idDemande).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: demande "+idDemande+" not found."));
        List<ScoreEntrepriseParParametre> scores = scoreEntrepriseParParametreRepository.findByDemandeByOrderByParametreAsc(demande.getId());

        return dtoFactory.createListScoreEntrepriseParParametre(scores);
    }

    @Override
    public ScoreEntrepriseParParametreDTO getScoreDemandeParParametre(Long idDemande, Long parametreId) throws ScoreEntrepriseParParametreException {
        //Entreprise entreprise = entrepriseRepository.findById(entrepriseId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: entreprise "+entrepriseId+" not found."));
    	DemandeScoring demande = demandeScoringRepository.findById(idDemande).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: demande "+idDemande+" not found."));
    	Parametre parametre = parametreRepository.findById(parametreId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: parametre "+parametreId+" not found."));
        ScoreEntrepriseParParametre score = scoreEntrepriseParParametreRepository.findByDemandeScoringAndParametre(demande, parametre).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: not found."));

        return dtoFactory.createScoreEntrepriseParParametre(score);
    }



    @Override
    public ScoreEntrepriseParParametreDTO getScoreDemandeParParametreOrNull(Long demandeId, Long parametreId) throws ScoreEntrepriseParParametreException {
        DemandeScoring demande = demandeScoringRepository.findById(demandeId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: demande "+demandeId+" not found."));
        Parametre parametre = parametreRepository.findById(parametreId).orElseThrow(() -> new ScoreEntrepriseParParametreException("ScoreEntrepriseParParametre :: parametre "+parametreId+" not found."));
        ScoreEntrepriseParParametre score = scoreEntrepriseParParametreRepository.findByDemandeScoringAndParametre(demande, parametre).orElse(null);

        return dtoFactory.createScoreEntrepriseParParametre(score);
    }

    @Override
    public ScoreEntrepriseParParametreDTO saveScore(ScoreEntrepriseParParametreDTO score) throws ScoreEntrepriseParParametreException{
        if (score.getDemandeScoringDTO() == null || (score.getDemandeScoringDTO() != null && score.getDemandeScoringDTO().getId() == null))
            throw new ScoreEntrepriseParParametreException("La demande est obligatoire.");

        if (score.getParametre() == null || (score.getParametre() != null && score.getParametre().getId() == null))
            throw new ScoreEntrepriseParParametreException("Le parametre est obligatoire.");

        //contraintes sur le score

        ScoreEntrepriseParParametre model = modelFactory.createScoreEntrepriseParParametre(score);
        scoreEntrepriseParParametreRepository.save(model);
        score.setId(model.getId());
        return score;
    }
}
