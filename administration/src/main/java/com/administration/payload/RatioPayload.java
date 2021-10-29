package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatioPayload {
	
	private Long 			id;
	private String 			code;
	private String 			libelle;
	private String 			formule;
	private Long 			ponderation;
	private String 			unite;
}
