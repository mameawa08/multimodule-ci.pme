package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReponsePayload {
	
	private Long 			id;
	private String 			code;
	private String 			libelle;
	private Long 			idQuestion;
	private int 			score;
}
