package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepondantPayload {

	private Long id;
	private String nom;
	private String prenom;
	private String mobile;
	private String email;
	private String fonction;
	private int entreprise;
}
