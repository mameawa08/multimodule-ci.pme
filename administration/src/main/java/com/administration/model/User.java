package com.administration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profil_id", nullable = false)
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
