package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicateurPayload {

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
	private double dfTotalRessources;
	private double djDettesFournisseurs;
	private double raAchats;
	private double xdExcedentBrutExploit;
	private double rmChargesFinancieres;
	private int annee;
	private int entreprise;
}
