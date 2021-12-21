package com.scoring.repository;

import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Parametre;
import com.scoring.models.ScoreEntrepriseParParametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreEntrepriseParParametreRepository extends JpaRepository<ScoreEntrepriseParParametre, Long> {

    Optional<ScoreEntrepriseParParametre> findByDemandeScoringAndParametre(DemandeScoring demande, Parametre parametre);

    List<ScoreEntrepriseParParametre> findByDemandeScoring(DemandeScoring demande);

    @Query("SELECT s FROM ScoreEntrepriseParParametre s WHERE s.demandeScoring.id = :idDemande ORDER BY s.parametre.id ASC ")
    List<ScoreEntrepriseParParametre> findByDemandeByOrderByParametreAsc(Long idDemande);
}
