package com.administration.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profils")
public class Profil implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4141027937985304648L;

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
