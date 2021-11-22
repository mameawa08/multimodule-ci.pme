package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class ScoresParPMEDTO {
	
    private Long id;
	
	private double score_financier;

    private double score_final;
    
    private EntrepriseDTO  entrepriseDTO;

}
