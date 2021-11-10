package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class Reponse_par_entreprise_DTO {
	
    private Long id;

	private boolean reponse_eligibilite;
	
	private EntrepriseDTO  entrepriseDTO;
	
	private QuestionDTO  questionDTO;
	
	private ReponseQualitativeDTO  reponse_quali_DTO;
	
	

}
