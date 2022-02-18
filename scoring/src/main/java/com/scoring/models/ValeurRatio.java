package com.scoring.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "valeur_ratio")
public class ValeurRatio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439982280011879092L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "valeur_ratio_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column(precision = 20, scale = 3)
	private BigDecimal valeur;

    @Column
    private int classe;

    @ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demandeScoring;
    
    @Column
	private Long idRatio;

}
