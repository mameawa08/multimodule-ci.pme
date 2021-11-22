package com.scoring.repository;

import com.scoring.models.ReponseQualitative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseQualitativeRepository extends JpaRepository<ReponseQualitative, Long> {
	
}
