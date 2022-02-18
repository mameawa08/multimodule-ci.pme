package com.administration.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "niveau_etude")
public class NiveauEtude {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "niveau_etude_seq", allocationSize = 1000)
    private Long id;
    private String libelle;
    private int actif;
}
