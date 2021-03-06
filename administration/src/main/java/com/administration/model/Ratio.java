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
@Table(name = "ratio")
public class Ratio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2029430097177604663L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
	@SequenceGenerator(name = "scoring_generator", sequenceName = "ratio_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;
	
	@Column
	private String libelle;

    @Column
    private String formule;
    
    @Column
    private Long ponderation;
    
    @Column
    private String unite;
    
    @Column(name = "actif")
	private int	actif;
    
    @Column
    private Long compteur;

}
