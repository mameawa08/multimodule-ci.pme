package com.scoring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceJointeDTO {
	
	private Long id;
	private String nomPiece;
	private Date DateCreation;
	private byte[] contenu;
	private IndicateurDTO indicateur;
	private boolean actif;
}
