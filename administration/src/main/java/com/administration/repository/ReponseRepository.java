package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.ReponseQualitative;


public interface ReponseRepository extends JpaRepository<ReponseQualitative, Long> {
	
	@Query("SELECT DISTINCT r FROM ReponseQualitative r WHERE r.actif = 1 ORDER BY r.id ASC")
	List<ReponseQualitative> findAllActif();
	
	@Query("SELECT DISTINCT r FROM ReponseQualitative r WHERE r.actif = 1 AND r.question IS NOT NULL AND r.question.id=:idQuestion ORDER BY r.id ASC")
	List<ReponseQualitative> findReponseByQuestion(@Param("idQuestion") Long idQuestion);
	
	@Query("SELECT COUNT(r) FROM ReponseQualitative r WHERE r.actif = 1 AND r.question IS NOT NULL AND r.question.id=:idQuestion")
	int findNbreReponseByQuestion(@Param("idQuestion") Long idQuestion);

}
