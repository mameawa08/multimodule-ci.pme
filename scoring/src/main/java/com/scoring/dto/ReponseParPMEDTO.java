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
	
	private DemandeScoringDTO  demande_scoringDTO;
	
	private Long  idQuestion;
	
	private Long  id_reponse_quali;
	
	

}
