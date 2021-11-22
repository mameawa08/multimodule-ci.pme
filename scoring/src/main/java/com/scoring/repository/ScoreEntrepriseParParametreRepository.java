package com.scoring.repository;

import com.scoring.models.Entreprise;
import com.scoring.models.Parametre;
import com.scoring.models.ScoreEntrepriseParParametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreEntrepriseParParametreRepository extends JpaRepository<ScoreEntrepriseParParametre, Long> {

    Optional<ScoreEntrepriseParParametre> findByEntrepriseAndParametre(Entreprise entreprise, Parametre parametre);

    List<ScoreEntrepriseParParametre> findByEntreprise(Entreprise entreprise);
}
