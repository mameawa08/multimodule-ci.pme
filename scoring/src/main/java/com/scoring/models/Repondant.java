package com.scoring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repondant")
public class Repondant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String prenom;
	private String mobile;
	private String email;
	private String fonction;
	private boolean actif;
	@ManyToOne
	@JoinColumn(name = "entreprise_id")
	private Entreprise entreprise;
	
}
