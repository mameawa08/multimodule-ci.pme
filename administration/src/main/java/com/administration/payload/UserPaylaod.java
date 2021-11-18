package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPaylaod {
    
	private Long id;

    private String email;

    private String username;

    private String password;

    private String confirmationPassword;

    private String nom;

    private String prenom;

    private String mobile;

    private String entrepriseLibelle;

    private String fonction;

    private int profil;

}
