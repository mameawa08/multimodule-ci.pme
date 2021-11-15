package com.scoring.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceJointePayload {
	
	private Long id;
	private String nomPiece;
	private Date DateCreation;
	private byte[] contenu;
	private int indicateur;
}
