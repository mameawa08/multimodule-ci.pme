package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class ReponseParPMEDTO {
	
    private Long id;

	private boolean reponse_eligibilite;
	
	private DemandeScoringDTO  demandeScoringDTO;
	
	private Long  idQuestion;
	
	private Long  id_reponse_quali;
	
	

}
