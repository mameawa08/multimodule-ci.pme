package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.administration.model.Parametre;

public interface ParametreRepository extends JpaRepository<Parametre, Long> {
	
	@Query("SELECT DISTINCT p FROM Parametre p WHERE p.actif = 1 ORDER BY p.id ASC")
	List<Parametre> findAllActif();

}
