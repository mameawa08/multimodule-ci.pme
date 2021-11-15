package com.scoring.repository;

import com.scoring.models.Dirigeant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirigeantRepository extends JpaRepository<Dirigeant, Long> {
	
}
