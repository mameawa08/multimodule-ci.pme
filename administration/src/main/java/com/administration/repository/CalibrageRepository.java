package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.Calibrage;


public interface CalibrageRepository extends JpaRepository<Calibrage, Long> {
	
	@Query("SELECT DISTINCT c FROM Calibrage c WHERE c.actif = 1 ORDER BY c.id ASC")
	List<Calibrage> findAllActif();
	
	@Query("SELECT DISTINCT c FROM Calibrage c WHERE c.actif = 1 AND c.ratio IS NOT NULL AND c.ratio.id=:idRatio ORDER BY c.id ASC")
	List<Calibrage> findCalibrageByRatio(@Param("idRatio") Long idRatio);

}
