package com.scoring.repository;

import java.util.List;

import com.scoring.models.Indicateur;
import com.scoring.models.PieceJointe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long> {
	
	public List<PieceJointe> findByIndicateur(Indicateur indicateur);
	
}
