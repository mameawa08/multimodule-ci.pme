package com.scoring.repository;

import com.scoring.models.Repondant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepondantRepository extends JpaRepository<Repondant, Long> {
	
}
