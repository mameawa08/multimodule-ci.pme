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
	
	private Long valeur;

    private int classe;
    
    private EntrepriseDTO entrepriseDTO;
   
	private Long idRatio;

}
