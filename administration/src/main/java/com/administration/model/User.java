package com.administration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "actif")
    private int actif;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profil profil;

    @Column(name = "mdpamodifier")
    private int mdpModifie;

    @Column(name = "mot_de_passe_precedent")
    private String motDePassePrecedent;

    @Column(name = "date_mdp_modification", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date dateModificationMdp;

    @Column(columnDefinition = "TEXT default NULL")
    private String resetPasswordToken;

    @Column
    private int confirme;

}
