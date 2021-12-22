package com.scoring.repository;

import java.util.List;
import java.util.Optional;

import com.scoring.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.ValeurRatio;

@Repository
public interface ValeurRatioRepository extends JpaRepository<ValeurRatio, Long> {

	@Query("SELECT DISTINCT v FROM ValeurRatio v WHERE v.demandeScoring IS NOT NULL AND v.demandeScoring.id=:idDemande")
	List<ValeurRatio> findValeurRatioByDemande(@Param("idDemande") Long idDemande);

	Optional<ValeurRatio> findByDemandeScoring_IdAndIdRatio(Long entreprise, Long idRatio);
}
