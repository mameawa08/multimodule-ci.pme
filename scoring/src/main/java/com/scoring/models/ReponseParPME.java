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

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "reponse_par_entreprise")
public class ReponseParPME implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4337523470652513371L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(nullable=true)
	private boolean reponse_eligibilite;
	
	@ManyToOne
	@JoinColumn(name = "id_entreprise")
	private Entreprise  entreprise;
	
	@Column(name = "id_question")
	private Long  idQuestion;
	
	@Column(name = "id_reponse_quali")
	private Long  id_reponse_quali;
	
	

}
