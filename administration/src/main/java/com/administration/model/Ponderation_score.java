package com.administration.model;

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
@Table(name = "ponderation")
public class Ponderation_score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6089947712411846958L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
	@SequenceGenerator(name = "scoring_generator", sequenceName = "ponderation_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String typeScore;

    @Column
    private Long ponderation;
    
    @Column(name = "actif")
	private int	actif;
    
    @ManyToOne
  	@JoinColumn(name = "id_parametre", nullable = true)
    private Parametre  parametre;
    
    @Column
    private Long compteur;

}
