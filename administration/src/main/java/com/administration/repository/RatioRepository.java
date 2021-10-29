package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.Ratio;

public interface RatioRepository extends JpaRepository<Ratio, Long> {
	
	@Query("SELECT DISTINCT r FROM Ratio r WHERE r.actif = 1 ORDER BY r.id ASC")
	List<Ratio> findAllActif();
	
	@Query("SELECT DISTINCT r FROM Ratio r WHERE r.actif = 1 AND r.code=:code ORDER BY r.id ASC")
	List<Ratio> findRatioByCode(@Param("code") String code);

}
