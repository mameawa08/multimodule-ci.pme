package com.scoring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "demande_scoring")
public class DemandeScoring implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -525426356710597301L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
    
	@ManyToOne
	@JoinColumn(name = "id_entreprise")
	private Entreprise entreprise;
    
    @Column
    private int status;
    
    @Type(type="text")
    private String motif_rejet;
    


}