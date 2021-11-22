package com.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scoring.models.ScoresParPME;

@Repository
public interface ScoreParPMERepository extends JpaRepository<ScoresParPME, Long> {

    
}
