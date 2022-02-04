package com.scoring.repository;

import com.scoring.models.AccompagnementAEligibilte;
import com.scoring.models.DemandeAccompagnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccompagnementAEligibiliteRepository extends JpaRepository<AccompagnementAEligibilte, Long> {

    List<AccompagnementAEligibilte> findByDemandeAccompagnement_Id(Long idDemande);

    AccompagnementAEligibilte findByDemandeAccompagnement_IdAndQuestionEligibilite(Long demande, Long question);
}
