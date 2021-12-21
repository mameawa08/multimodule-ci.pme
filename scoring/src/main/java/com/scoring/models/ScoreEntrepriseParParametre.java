package com.scoring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "score_entreprise_par_parametre")
public class ScoreEntrepriseParParametre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demande_scoring;

    @ManyToOne
    @JoinColumn(name = "parametre_id")
    private Parametre parametre;

    private double score;
}
