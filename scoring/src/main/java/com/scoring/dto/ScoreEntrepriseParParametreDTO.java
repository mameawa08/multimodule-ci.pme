package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ScoreEntrepriseParParametreDTO {

    private Long id;

    private EntrepriseDTO entreprise;

    private ParametreDTO parametre;

    private double score;
}
