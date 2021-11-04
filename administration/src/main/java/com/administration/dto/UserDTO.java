package com.administration.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable {


    private Long id;

    private String email;

    private String identifiant;

    private String motDePasse;

    private String nom;

    private String prenom;

    private int actif;

    private ProfilDTO profil;

    private int mdpModifie;

    private String motDePassePrecedent;

    private Date dateModificationMdp;

    private String resetPasswordToken;

    private int confirme;

    private List<String> habilitations;

}
