package com.administration.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class RatioDTO {

    private Long id;
	
	private String code;
	
	private String libelle;

    private String formule;
    
    private Long ponderation;
    
    private String unite;
    
	private int	actif;

}
