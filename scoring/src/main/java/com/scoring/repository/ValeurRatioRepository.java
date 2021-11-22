package com.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scoring.models.ValeurRatio;

@Repository
public interface ValeurRatioRepository extends JpaRepository<ValeurRatio, Long> {

    
}
