package com.administration.dto; 

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class HabilitationDTO implements Serializable {

    private Long				id;

    private String				code;

    private String				libelle;

}
