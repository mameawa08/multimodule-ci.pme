package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {

	private Long id;

    private String email;

    private String username;

    private String password;

    private String nom;

    private String prenom;

    private int actif;

//    private ProfilDTO profil;

    private int mdpModifie;

    private String motDePassePrecedent;

    private Date dateModificationMdp;

    private String resetPasswordToken;

    private int confirme;

    private List<String> habilitations;

    private String confirmationToken;

    private String fonction;

    private Long entrepriseId;

    private String entrepriseLibelle;

    private String mobile;

}
