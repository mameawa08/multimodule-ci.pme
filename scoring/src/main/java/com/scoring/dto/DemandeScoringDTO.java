package com.scoring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class DemandeScoringDTO {
	
    private Long id;
    
	private EntrepriseDTO entrepriseDTO;
  
    private int status;
   
    private String motif;

    private Date dateEnvoie;

    private Date dateReception;
    
    private boolean rapportGenere;
    
    private Date dateCreation;
    
	private boolean 	repEli=false;
	
	private boolean 	indicateurAjoute;
	
	private boolean 	repQuali;
	
	private String libelleStatut;
	
	private double scoreFinal;

    private Long traiterPar;

}
