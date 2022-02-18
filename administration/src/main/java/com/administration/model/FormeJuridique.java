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
@Table(name = "forme_juridique")
public class FormeJuridique implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2662576183122632626L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
	@SequenceGenerator(name = "scoring_generator", sequenceName = "forme_juridique_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    
    @Column(name = "actif")
	private int	actif;

}
