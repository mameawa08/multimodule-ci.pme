package com.administration.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class Ponderation_scoreDTO {

    private Long id;
	
	private String code_score;

    private Long ponderation;
    
	private int	actif;
    
    private ParametreDTO  parametreDTO;

}
