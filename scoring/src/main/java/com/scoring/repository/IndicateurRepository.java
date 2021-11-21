package com.scoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {

    public List<Indicateur> findByEntreprise(Entreprise entreprise);
    
    @Query("SELECT DISTINCT i FROM Indicateur i WHERE i.actif = 1 AND i.entreprise IS NOT NULL AND i.entreprise.id=:idEntreprise "
    		+ "AND i.annee=MAX(i.annee) ORDER BY i.id ASC")
	Indicateur findLastIndicateurByEntreprise(@Param("idEntreprise") Long idEntreprise);
	
}
