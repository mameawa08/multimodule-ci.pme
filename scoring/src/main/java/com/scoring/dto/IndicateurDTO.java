package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicateurDTO {

	private Long id;
	private double bkActifCirculant;
	private double btTresorerieActif;
	private double dpPassifCirculant;
	private double dtTresoreriePassif;
	private double xiResultatNet;
	private double xbChiffresDaffaires;
	private double biCreanceClient;
	private double caf;
	private double caCapitauxPropres;
	private double dfTotalResources;
	private double djDettesFournisseurs;
	private double raAchats;
	private int annee;
	private EntrepriseDTO entreprise;
	private boolean actif;
	private double xdExcedentBrutExploit;
	private double rmChargesFinancieres;
		
}
