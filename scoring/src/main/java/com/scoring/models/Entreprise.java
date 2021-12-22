package com.scoring.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entreprise")
public class Entreprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long 		id;
	private String 		raisonSociale;
	private int 		annee;
	private String 		intitule;
	private Long 		capital;
	@ElementCollection
	private List<Long> secteurs;
	private String 		description;
	private String 		regime;
	private String 		adresse;
	private String 		siteWeb;
	private String 		logo;
	private boolean 	eligible;
	private boolean 	actif;
	
	@OneToOne(mappedBy = "entreprise")
	private Dirigeant dirigeant;

	private Long formeJuridique;
}


