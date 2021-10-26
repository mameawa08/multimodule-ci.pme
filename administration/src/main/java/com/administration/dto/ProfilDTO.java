package com.administration.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProfilDTO implements Serializable {

    private Long id;

    private String libelle;

    private String code;

    private int actif;

    private List<HabilitationDTO> habilitations;
}
