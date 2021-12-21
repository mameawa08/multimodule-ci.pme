package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntrepriseDTO {
	
	private Long 		id;
	private String raisonSociale;
	private int 		annee;
	private String 		intitule;
	private Long 		capital;
	private List<SecteurActiviteDTO> secteurs;
	private String 		description;
	private String 		regime;
	private String 		adresse;
	private String 		siteWeb;
	private String 		logo;
	private boolean 	eligible;
	private boolean 	actif;

	private DirigeantDTO dirigeant;
	private FormeJuridiqueDTO		 formeJur;
}
