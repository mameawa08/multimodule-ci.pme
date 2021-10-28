package com.administration.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class QuestionDTO {

    private Long id;
	
	private String code;

    private String libelle;
    
	private int	actif;
    
    private ParametreDTO  parametreDTO;

}
