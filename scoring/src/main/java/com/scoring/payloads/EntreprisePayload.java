package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntreprisePayload {
	
	private Long 		id;
	private String 	raisonSociale;
	private int 		annee;
	private Long 		capital;
	private String 		secteur;
	private String 		description;
	private String 		regime;
	private String 		adresse;
	private String 		siteWeb;
	private String 		logo;
	private boolean 	eligible;
	private String 		formeJur;

}
