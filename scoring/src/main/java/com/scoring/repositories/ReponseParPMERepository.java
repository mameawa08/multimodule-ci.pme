package com.scoring.repositories;

import com.scoring.models.Reponse_par_entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseParPMERepository extends JpaRepository<Reponse_par_entreprise, Long> {
	
}
