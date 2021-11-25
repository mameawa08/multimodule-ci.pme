package com.scoring.repository;

import java.util.List;
import java.util.Optional;

import com.scoring.models.Entreprise;
import com.scoring.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scoring.models.ReponseParPME;

@Repository
public interface ReponseParPMERepository extends JpaRepository<ReponseParPME, Long> {
	
	@Query("SELECT DISTINCT r FROM ReponseParPME r WHERE r.entreprise IS NOT NULL AND r.entreprise.id=:idEntreprise "
			+ "AND r.id_reponse_quali IS NULL ORDER BY r.id ASC")
	List<ReponseParPME> findRepQuestEliByEntreprise(@Param("idEntreprise") Long idEntreprise);

	List<ReponseParPME> findByEntreprise(Entreprise entreprise);

    Optional<ReponseParPME> findByEntrepriseAndIdQuestion(Entreprise entreprise, Long question);

    @Query("SELECT r FROM ReponseParPME r WHERE r.entreprise.id = :entreprise AND r.id_reponse_quali IS NOT NULL")
    List<ReponseParPME> findReponseParPMEQualitatifByEntreprise(Long entreprise);
}
