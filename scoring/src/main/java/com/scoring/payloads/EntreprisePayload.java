package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntreprisePayload {
	
	private Long 			id;
	private String 			raisonSociale;
	private int 			annee;
	private Long 			capital;
	private List<Integer> 	secteurs;
	private String 			description;
	private String 			regime;
	private String 			adresse;
	private String 			siteWeb;
	private String 			logo;
	private boolean 		eligible;
	private int 			formeJuridique;

}
