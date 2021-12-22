package com.scoring.repository;

import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.ScoresParPME;

import java.util.Optional;

@Repository
public interface ScoreParPMERepository extends JpaRepository<ScoresParPME, Long> {

	@Query("SELECT DISTINCT s FROM ScoresParPME s WHERE s.demandeScoring IS NOT NULL AND s.demandeScoring.id=:idDemande")
	ScoresParPME findScoreByDemande(@Param("idDemande") Long idDemande);

	Optional<ScoresParPME> findByDemandeScoring(DemandeScoring demande);
    
}
