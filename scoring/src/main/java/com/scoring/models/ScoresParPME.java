package com.scoring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "scores_par pme")
public class ScoresParPME implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339365313614904130L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private double score_financier;

    @Column
    private double score_final;
    
    @ManyToOne
  	@JoinColumn(name = "id_entreprise", nullable = true)
    private Entreprise  entreprise;

}
