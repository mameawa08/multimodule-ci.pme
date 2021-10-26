package com.administration.modele;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author agileway
 */

@Entity
@Table(name = "reponse_qualitative")
public class ReponseQualitative implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5297205263579630551L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    
    @Column
    private int score;
    
    @ManyToOne
  	@JoinColumn(name = "id_pquestion", nullable = true)
    private Question  question;
    
    @Column(name = "actif")
	private int	actif;

}
