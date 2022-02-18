package com.scoring.models;

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
@Table(name = "reponse_par_entreprise")
public class ReponseParPME implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4337523470652513371L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "reponse_par_entreprise_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column(nullable=true)
	private boolean reponseEligibilite;
	
	@ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demandeScoring;
	
	@Column(name = "id_question")
	private Long  idQuestion;
	
	@Column(name = "id_reponse_quali")
	private Long  id_reponse_quali;
	
	

}
