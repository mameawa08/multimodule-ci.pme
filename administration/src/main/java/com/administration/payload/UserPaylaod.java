package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPaylaod {
    
	private Long id;

    private String email;

    private String identifiant;

    private String motDePasse;

    private String confirmationMotDePasse;

    private String nom;

    private String prenom;

    private int profil;

}
