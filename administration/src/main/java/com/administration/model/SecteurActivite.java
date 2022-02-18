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
@Table(name = "secteur_activite")
public class SecteurActivite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4468409826658158461L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
	@SequenceGenerator(name = "scoring_generator", sequenceName = "secteur_activite_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;

    @Column
    private String libelle;
    
    @Column(name = "actif")
	private int	actif;

}
