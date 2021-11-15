package com.administration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;

    @Column
    private String libelle;
    
    @Column(name = "actif")
	private int	actif;

}
