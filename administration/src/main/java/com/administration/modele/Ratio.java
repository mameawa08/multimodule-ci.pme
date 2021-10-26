package com.administration.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author agileway
 */

@Entity
@Table(name = "ratio")
public class Ratio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2029430097177604663L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String formule;
    
    @Column
    private Long ponderation;
    
    @Column
    private String unite;
    
    @Column(name = "actif")
	private int	actif;

}
