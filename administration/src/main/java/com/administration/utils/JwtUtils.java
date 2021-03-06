package com.administration.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
// import sn.mfb.config.entities.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.oauth.jwt.secret}")
    private String jwtSecret;
//
//    @Value("${ecourrier.app.jwtExpirationMs}")
//    private int jwtExpirationMs;

    // From Medium

    @Value("${app.security.jwt.keystore-location}")
	private String keyStorePath;
	
	@Value("${app.security.jwt.keystore-password}")
	private String keyStorePassword;
	
	@Value("${app.security.jwt.key-alias}")
	private String keyAlias;
	
	@Value("${app.security.jwt.private-key-passphrase}")
	private String privateKeyPassphrase;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }


    public String generateJwtToken(String payload, int expiration) throws UnsupportedEncodingException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        //Key key = keyStore().getKey(keyAlias, privateKeyPassphrase.toCharArray());
        return Jwts.builder()
                .setSubject((payload))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                //.signWith(key)
              .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        try{
            return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
        }
        catch (ExpiredJwtException e){
            return e.getClaims().getSubject();
        }
    }

    public boolean validateJwtToken(String authToken) throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException, UnsupportedEncodingException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        try {
            //Key key = keyStore().getKey(keyAlias, privateKeyPassphrase.toCharArray());
          //  Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
        	Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new SignatureException("Invalid JWT signature: "+ e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new MalformedJwtException("Invalid JWT token: "+ e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw new ExpiredJwtException(null, null, "JWT token is expired: "+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new UnsupportedJwtException("JWT token is unsupported: "+ e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new IllegalArgumentException("JWT claims string is empty: "+ e.getMessage());
        }
       /* catch (UnrecoverableKeyException e) {
            throw new UnrecoverableKeyException(e.getMessage());
        } catch (KeyStoreException e) {
            throw new KeyStoreException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(e.getMessage());
        }*/
    }
}
