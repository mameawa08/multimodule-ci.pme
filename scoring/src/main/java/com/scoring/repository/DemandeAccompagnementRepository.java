package com.scoring.repository;

import com.scoring.models.DemandeAccompagnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeAccompagnementRepository extends JpaRepository<DemandeAccompagnement, Long> {

    DemandeAccompagnement findByDemandeScoring_Id(Long idDemande);

    List<DemandeAccompagnement> findByDemandeScoring_Entreprise_Id(Long idEntreprise);
}
