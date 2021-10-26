package com.administration.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profils")
public class Profil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profil_id")
    private Long id;

    @Column(name="libelle", unique = true)
    private String libelle;

    @Column(name="code")
    private String code;

    @Column(name = "actif")
    private int actif;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "habilitation_par_profil", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "habilitation_id"))
    private List<Habilitation> habilitations;
}
