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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1812602355944843550L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "habilitation_seq", allocationSize = 1000)
    @Column(name = "id")
    private Long				id;

    @Column(name = "code", unique = true)
    private String				code;

    @Column(name = "libelle")
    private String				libelle;

}
