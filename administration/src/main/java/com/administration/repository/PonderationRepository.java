package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.Ponderation_score;


public interface PonderationRepository extends JpaRepository<Ponderation_score, Long> {
	
	@Query("SELECT DISTINCT p FROM Ponderation_score p WHERE p.actif = 1 ORDER BY p.id ASC")
	List<Ponderation_score> findAllActif();
	
	@Query("SELECT DISTINCT p FROM Ponderation_score p WHERE p.actif = 1 AND p.parametre IS NOT NULL AND p.parametre.id=:idParametre ")
	Ponderation_score findPonderationByParametre(@Param("idParametre") Long idParametre);
	
	@Query("SELECT DISTINCT p FROM Ponderation_score p WHERE p.actif = 1 AND p.parametre IS NULL ")
	Ponderation_score findPonderationScoreFinancier();
	
	@Query("SELECT  COUNT(p) FROM Ponderation_score p WHERE p.actif = 1")
	int findNbrePonderation();
}
