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
@Table(name = "reponse_qualitative")
public class ReponseQualitative implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5297205263579630551L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "reponse_qualitative_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    
    @Column
    private int score;
    
    @ManyToOne
  	@JoinColumn(name = "id_question")
    private Question  question;
    
    @Column(name = "actif")
	private int	actif;

}
