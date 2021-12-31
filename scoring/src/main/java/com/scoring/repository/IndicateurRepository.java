package com.scoring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.DemandeScoring;
import com.scoring.models.Entreprise;
import com.scoring.models.Indicateur;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {

    public List<Indicateur> findByDemandeScoringOrderByAnneeDesc(DemandeScoring demande);

    @Query("SELECT DISTINCT i FROM Indicateur i WHERE i.annee= "
    		+ "(SELECT MAX(i2.annee) FROM Indicateur i2 WHERE i2.demandeScoring.id=:idDemande AND i2.demandeScoring.id=i.demandeScoring.id)")
	Indicateur findLastIndicateurByDemande(@Param("idDemande") Long idDemande);

    Optional<Indicateur> findByDemandeScoringAndAnnee(DemandeScoring demande, int annee);
}
