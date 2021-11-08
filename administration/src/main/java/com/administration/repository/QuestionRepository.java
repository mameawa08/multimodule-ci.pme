package com.administration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.administration.model.Question;


public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	@Query("SELECT DISTINCT q FROM Question q WHERE q.actif = 1 ORDER BY q.id ASC")
	List<Question> findAllActif();
	
	@Query("SELECT DISTINCT q FROM Question q WHERE q.actif = 1 AND q.parametre IS NULL ORDER BY q.id ASC")
	List<Question> findQuestionEligible();
	
	@Query("SELECT DISTINCT q FROM Question q WHERE q.actif = 1 AND q.parametre IS NOT NULL ORDER BY q.id ASC")
	List<Question> findQuestionQualitative();
	
	@Query("SELECT DISTINCT q FROM Question q WHERE q.actif = 1 AND q.parametre IS NOT NULL AND q.parametre.id=:idParametre ORDER BY q.id ASC")
	List<Question> findQuestionByParametre(@Param("idParametre") Long idParametre);
	
	@Query("SELECT COUNT(q) FROM Question q WHERE q.actif = 1 AND q.parametre IS NOT NULL AND q.parametre.id=:idParametre")
	int findNbreQuestionByParametre(@Param("idParametre") Long idParametre);
	
	@Query("SELECT COUNT(q) FROM Question q WHERE q.actif = 1 AND q.parametre IS NULL")
	int findNBreQuestionEligible();

}
