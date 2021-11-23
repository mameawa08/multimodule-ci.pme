package com.scoring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {

    public List<Indicateur> findByEntreprise(Entreprise entreprise);
    
    @Query("SELECT DISTINCT i FROM Indicateur i WHERE i.annee= "
    		+ "(SELECT MAX(i2.annee) FROM Indicateur i2 WHERE i2.entreprise.id=:idEntreprise AND i2.entreprise.id=i.entreprise.id)")
	Indicateur findLastIndicateurByEntreprise(@Param("idEntreprise") Long idEntreprise);

    Optional<Indicateur> findByEntrepriseAndAnnee(Entreprise entreprise, int annee);
}
