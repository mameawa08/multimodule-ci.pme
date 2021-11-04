package com.administration.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class ParametreDTO {
	
    private Long id;
	
	private String code;

    private String libelle;
    
    private int nbre_question;
    
	private int	actif;

}
