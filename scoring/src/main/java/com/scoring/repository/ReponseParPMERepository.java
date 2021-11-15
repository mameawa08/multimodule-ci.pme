package com.scoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scoring.models.ReponseParPME;

@Repository
public interface ReponseParPMERepository extends JpaRepository<ReponseParPME, Long> {
	
}
