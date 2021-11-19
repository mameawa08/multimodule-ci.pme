package com.scoring.models;

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
@Table(name = "valeur_ratio")
public class ValeurRatio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439982280011879092L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private Long valeur;

    @Column
    private int classe;
    
    @Column
    private Entreprise entreprise;
    
    @Column
	private Long idRatio;

}
