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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "score_entreprise_par_parametre_seq", allocationSize = 1000)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demandeScoring;

    @ManyToOne
    @JoinColumn(name = "parametre_id")
    private Parametre parametre;

    private double score;
}
