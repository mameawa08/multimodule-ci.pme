package com.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.DemandeScoring;


import java.util.Collection;
import java.util.List;

@Repository
public interface DemandeScoringRepository extends JpaRepository<DemandeScoring, Long> {

    List<DemandeScoring> findAllByOrderByIdDesc();

    @Query("SELECT DISTINCT d FROM DemandeScoring d WHERE d.entreprise.id = :idEntreprise and d.status = :status ")
	DemandeScoring findDemandeByStatus(@Param("idEntreprise") Long idEntreprise, @Param("status") int status);

    DemandeScoring findByEntreprise_IdAndStatusNotIn(Long idEntreprise, List<Integer> status);

    @Query("SELECT DISTINCT d FROM DemandeScoring d WHERE d.entreprise.id=:idEntreprise and d.status != 6 ")
    DemandeScoring findDemandeNonClotureParEntreprise(@Param("idEntreprise") Long idEntreprise);


    DemandeScoring findFirstByEntreprise_IdOrderByDateCreationDesc(Long idEntreprise);
    
    @Query("SELECT DISTINCT d FROM DemandeScoring d WHERE d.entreprise.id=:idEntreprise AND d.status != 1 AND d.status != 6 ")
    DemandeScoring findDemandeEnvoyee(@Param("idEntreprise") Long idEntreprise);
}
