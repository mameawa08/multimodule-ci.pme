package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirigeantPayload {

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
	private Long numeroCI;

	private int entreprise;
}
