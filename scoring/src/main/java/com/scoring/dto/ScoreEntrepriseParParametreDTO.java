package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreEntrepriseParParametreDTO {

    private Long id;

    private DemandeScoringDTO demandeScoringDTO;

    private ParametreDTO parametre;

    private double score;
}
