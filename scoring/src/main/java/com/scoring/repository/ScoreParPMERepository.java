package com.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.ScoresParPME;

@Repository
public interface ScoreParPMERepository extends JpaRepository<ScoresParPME, Long> {

	@Query("SELECT DISTINCT s FROM ScoresParPME s WHERE s.entreprise IS NOT NULL AND d.entreprise.id=:idEntreprise")
	ScoresParPME findScoreByEntreprise(@Param("idEntreprise") Long idEntreprise);
    
}
