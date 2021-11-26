package com.scoring.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class ValeurRatioDTO {

    private Long id;
	
	private BigDecimal valeur;

    private int classe;
    
    private EntrepriseDTO entrepriseDTO;
   
	private Long idRatio;

}
