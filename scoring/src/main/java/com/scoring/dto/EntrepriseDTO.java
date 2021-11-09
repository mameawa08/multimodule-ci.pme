package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrepriseDTO {
	
	private Long 		id;
	private String 		raisonSocial;
	private int 		annee;
	private Long 		capital;
	private String 		secteur;
	private String 		description;
	private String 		regime;
	private String 		adresse;
	private String 		siteWeb;
	private String 		logo;
	private boolean 	eligible;
	private boolean 	actif;
}
