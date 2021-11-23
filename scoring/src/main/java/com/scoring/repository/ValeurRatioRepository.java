package com.scoring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.ValeurRatio;

@Repository
public interface ValeurRatioRepository extends JpaRepository<ValeurRatio, Long> {

	@Query("SELECT DISTINCT v FROM ValeurRatio v WHERE v.entreprise IS NOT NULL AND v.entreprise.id=:idEntreprise")
	List<ValeurRatio> findValeurRatioByEntreprise(@Param("idEntreprise") Long idEntreprise);
}
