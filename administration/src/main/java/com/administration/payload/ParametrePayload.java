package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParametrePayload {
	
	private Long 			id;
	private String 			code;
	private String 			libelle;
	private int 			nbreQuestion;
	private Long 			ponderation;
}
