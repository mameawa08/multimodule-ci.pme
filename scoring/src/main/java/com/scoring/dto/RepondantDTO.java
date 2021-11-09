package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepondantDTO {
	
	private Long id;
	private String nom;
	private String prenom;
	private String mobile;
	private String email;
	private String fonction;
	private boolean actif;
	private EntrepriseDTO entreprise;
}
