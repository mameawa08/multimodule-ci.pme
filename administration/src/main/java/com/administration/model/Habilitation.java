package com.administration.model; 

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "habilitations")
public class Habilitation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long				id;

    @Column(name = "code", unique = true)
    private String				code;

    @Column(name = "libelle")
    private String				libelle;

}