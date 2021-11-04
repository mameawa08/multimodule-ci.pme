package com.administration.model;

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
    
    @ManyToOne
  	@JoinColumn(name = "id_ratio")
    private Ratio  ratio;

}
