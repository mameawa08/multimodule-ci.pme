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
@Table(name = "parametre")
public class Parametre implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -525426356710597301L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
	@SequenceGenerator(name = "scoring_generator", sequenceName = "parametre_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    
    @Column
    private int nbre_question;
    
    @Column(name = "actif")
	private int	actif;

    @Column
    private Long compteur;
}
