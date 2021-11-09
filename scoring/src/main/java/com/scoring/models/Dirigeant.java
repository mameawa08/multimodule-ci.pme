package com.scoring.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dirigeant")
public class Dirigeant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private boolean actif;

	@OneToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;
}
