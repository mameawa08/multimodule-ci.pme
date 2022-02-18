package com.scoring.models;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "scores_par_pme")
public class ScoresParPME implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339365313614904130L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "scores_par_pme_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private double score_financier;

    @Column
    private double score_final;
    
    @ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demandeScoring;

}
