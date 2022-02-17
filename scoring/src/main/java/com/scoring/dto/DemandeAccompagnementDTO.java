package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DemandeAccompagnementDTO {

    private Long id;

    private DemandeScoringDTO demandeScoring;

    private int status;

    private String libelleStatut;

    private Date dateCreation;

    private Date dateEnvoi;

    private Date dateReception;

    private boolean questionnaireAjoute;

    private Long traiterPar;

}
