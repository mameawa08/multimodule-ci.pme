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
@Table(name = "question")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9024440466491855084L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "question_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    
    @Column(name = "actif")
	private int	actif;
    
    @ManyToOne
  	@JoinColumn(name = "id_parametre", nullable = true)
    private Parametre  parametre;
    
    @ManyToOne
  	@JoinColumn(name = "id_theme", nullable = true)
    private Theme  theme;

}
