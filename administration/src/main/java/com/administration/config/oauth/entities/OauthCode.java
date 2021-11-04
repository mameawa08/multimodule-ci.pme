package com.administration.config.oauth.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="oauth_code")
@Getter
@Setter
public class OauthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint unsigned")
    private Integer id;

    @Column(name="code")
    private String code;

    @Lob
    @Column(name="authentication", columnDefinition = "bytea")
    private byte[] authentication;
}
