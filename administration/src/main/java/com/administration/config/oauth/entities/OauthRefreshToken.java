package com.administration.config.oauth.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="oauth_refresh_token")
@Getter
@Setter
public class OauthRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "bigint unsigned")
    private Integer id;

    @Column(name="token_id")
    private String tokenId;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="token")
    private byte[] token;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="authentication", columnDefinition = "bytea")
    private byte[] authentication;

    @Column
    private Date expiration;

//    public OAuth2Authentication getAuthentication() {
//        return SerializableObjectConverter.deserialize(authentication);
//    }
//
//    public void setAuthentication(OAuth2Authentication authentication) {
//        this.authentication = SerializableObjectConverter.serialize(authentication);
//    }
}
