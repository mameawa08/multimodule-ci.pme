package com.scoring.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@Lob
	@Type(type = "org.hibernate.type.BlobType")
	private byte[] contenu;

	@OneToOne
	@JoinColumn(name = "indicateur_id")
	private Indicateur indicateur;
	private boolean actif;
}
