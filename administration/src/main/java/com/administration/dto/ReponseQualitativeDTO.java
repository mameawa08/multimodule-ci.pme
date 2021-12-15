package com.administration.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class ReponseQualitativeDTO {

	private Long id;
	
	private String code;

    private String libelle;
    
    private int score;
    
    private QuestionDTO  questionDTO;
    
	private int	actif;
	
	private Long compteur;

}
