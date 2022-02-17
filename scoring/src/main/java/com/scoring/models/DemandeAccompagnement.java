package com.scoring.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "demande_accomagnement")
public class DemandeAccompagnement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_demande_scoring")
    private DemandeScoring demandeScoring;

    private int status;

    private Date dateCreation;

    private Date dateEnvoi;

    private Date dateReception;

    @Column(columnDefinition = "boolean default false")
    private boolean questionnaireAjoute;

    @Column
    private Long traiterPar;
}
