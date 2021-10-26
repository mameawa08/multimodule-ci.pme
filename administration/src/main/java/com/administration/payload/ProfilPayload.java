package com.administration.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfilPayload {
	
	private Long 			id;
	private String 			code;
	private String 			libelle;
	private List<Integer> 	habilitations;
}
