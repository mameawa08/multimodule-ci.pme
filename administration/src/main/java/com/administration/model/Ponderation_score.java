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
@Table(name = "ponderation")
public class Ponderation_score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6089947712411846958L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code_score;

    @Column
    private Long ponderation;
    
    @Column(name = "actif")
	private int	actif;
    
    @ManyToOne
  	@JoinColumn(name = "id_parametre", nullable = true)
    private Parametre  parametre;

}
