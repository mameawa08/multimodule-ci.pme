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
@Table(name = "calibrage")
public class Calibrage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8013200006199539481L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private double min;

    @Column
    private double max;
    
    @Column
    private int classe;
    
    @Column(name = "actif")
	private int	actif;

}
