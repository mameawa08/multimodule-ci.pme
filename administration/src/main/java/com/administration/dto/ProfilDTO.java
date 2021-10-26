package com.administration.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilDTO {

    private Long id;

    private String libelle;

    private String code;

    private int actif;

    private List<HabilitationDTO> habilitations;
}
