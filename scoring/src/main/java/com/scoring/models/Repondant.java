package com.scoring.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repondant")
public class Repondant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "repondant_seq", allocationSize = 1000)
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
