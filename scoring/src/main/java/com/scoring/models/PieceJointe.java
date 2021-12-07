package com.scoring.models;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "piece_jointe")
public class PieceJointe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomPiece;
	private Date DateCreation;
//	@Lob
//	@Type(type = "org.hibernate.type.BlobType")
    @Column
	private byte[] contenu;

	@OneToOne
	@JoinColumn(name = "indicateur_id")
	private Indicateur indicateur;
	private boolean actif;
}
