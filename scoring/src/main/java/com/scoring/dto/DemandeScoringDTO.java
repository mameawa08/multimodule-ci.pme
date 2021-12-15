package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author agileway
 */

@Setter
@Getter
public class DemandeScoringDTO {
	
    private Long id;
    
	private EntrepriseDTO entrepriseDTO;
  
    private int status;
   
    private String motif_rejet;

    private Date dateEnvoie;

    private Date dateReception;
    


}
