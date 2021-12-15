package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.Ratio;

public interface RatioRepository extends JpaRepository<Ratio, Long> {
	
	@Query("SELECT DISTINCT r FROM Ratio r WHERE r.actif = 1 ORDER BY r.id ASC")
	List<Ratio> findAllActif();
	
	@Query("SELECT DISTINCT r FROM Ratio r WHERE r.actif = 1 AND LOWER(r.code) LIKE CONCAT('%',cast(LOWER(:code) AS text),'%') ORDER BY r.id ASC")
	Ratio findRatioByCode(@Param("code") String code);
	
	@Query("SELECT  COUNT(r) FROM Ratio r WHERE r.actif = 1")
	int findNbreRatio();

}
