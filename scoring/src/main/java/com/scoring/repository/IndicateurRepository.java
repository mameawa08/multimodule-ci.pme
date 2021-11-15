package com.scoring.repository;

import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {

    public List<Indicateur> findByEntreprise(Entreprise entreprise);
	
}
