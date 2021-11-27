package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class PonderationDTO {

    private Long id;
	
	private String typeScore;

    private Long ponderation;
    
	private int	actif;
    
    private ParametreDTO  parametreDTO;

}
