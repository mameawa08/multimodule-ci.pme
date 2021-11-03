package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PonderationPayload {
	
	private Long 			id;
	private String 			code_score;
	private Long 			ponderation;
	private Long 			idParametre;
}
