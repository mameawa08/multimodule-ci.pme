package com.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scoring.models.DemandeScoring;

import java.util.List;

@Repository
public interface DemandeScoringRepository extends JpaRepository<DemandeScoring, Long> {

    List<DemandeScoring> findAllByOrderByIdDesc();
}