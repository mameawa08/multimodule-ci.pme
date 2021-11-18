package com.scoring.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessTokenDetails {

    private String access_token;

    private String refresh_token;

    private Long userId;

    private Long entrepriseId;

    private String username;

    private int idProfil;

}
