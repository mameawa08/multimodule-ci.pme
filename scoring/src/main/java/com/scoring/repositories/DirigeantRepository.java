package com.scoring.repositories;

import com.scoring.models.Dirigeant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirigeantRepository extends JpaRepository<Dirigeant, Long> {
	
	@Query("SELECT DISTINCT d FROM Dirigeant d WHERE d.entreprise IS NOT NULL AND d.entreprise.id=:idEntreprise")
	Dirigeant findDirigeantByEntreprise(@Param("idEntreprise") Long idEntreprise);
	
}
