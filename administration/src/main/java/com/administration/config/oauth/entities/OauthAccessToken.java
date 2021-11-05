package com.administration.config.oauth.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="oauth_access_token")
@Getter
@Setter
public class OauthAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint unsigned")
    private Integer id;

    @Column(name="token_id")
    private String tokenId;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="token", columnDefinition = "bytea")
    private byte[] token;

    @Column(name="authentication_id")
    private String authenticationId;

    @Column(name="user_name")
    private String userName;

    @Column(name="client_id")
    private String clientId;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="authentication", columnDefinition = "bytea")
    private byte[] authentication;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column
    private Date expiration;

}
