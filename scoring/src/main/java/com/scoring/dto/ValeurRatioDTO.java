package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class ValeurRatioDTO {

    private Long id;
	
	private double valeur;

    private int classe;
    
    private EntrepriseDTO entrepriseDTO;
   
	private Long idRatio;

}
