package com.scoring.models;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
@Entity
@Table(name = "theme")
public class Theme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7765811165048834656L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "theme_seq", allocationSize = 1000)
	@Column(name="id")
    private Long id;
	
	@Column
	private String code;

    @Column
    private String libelle;
    

}
