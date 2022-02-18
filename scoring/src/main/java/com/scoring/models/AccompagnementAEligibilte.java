package com.scoring.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "accomagnement_a_eligibilite")
public class AccompagnementAEligibilte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "accomagnement_a_eligibilite_seq", allocationSize = 1000)
    private Long id;

    private Long questionEligibilite;

    @Type(type="text")
    private String accompagnement;

    @ManyToOne
    @JoinColumn(name = "id_demande_accompagnent")
    private DemandeAccompagnement demandeAccompagnement;

}
