package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirigeantDTO {
	
	private Long id;
	private String nom;
	private String prenom;
	private String mobile;
	private String niveau;
	private String email;
	private String sexe;
	private String date;
	private String lieu;
	private String nationalite;
	private String adresse;
	private String typePiece;
	private String numeroCI;
	private boolean actif;

	private EntrepriseDTO entreprise;
}
