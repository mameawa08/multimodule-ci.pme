package com.scoring.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "demande_scoring_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
    
	@ManyToOne
	@JoinColumn(name = "id_entreprise")
	private Entreprise entreprise;
    
    @Column
    private int status;
    
    @Type(type="text")
    private String motif;

    @Column(nullable = true)
    private Date dateEnvoie;

    @Column(nullable = true)
    private Date dateReception;
    
    @Column(nullable = true)
    private Date dateCreation;
    
    @Column(columnDefinition = "boolean default false")
    private boolean rapportGenere;
    
    @Column(columnDefinition = "boolean default false")
	private boolean 	repEli=false;
    
    @Column(columnDefinition = "boolean default false")
	private boolean 	indicateurAjoute =false;
    
    @Column(columnDefinition = "boolean default false")
	private boolean 	repQuali=false;

    @Column
    private Long traiterPar;
}
