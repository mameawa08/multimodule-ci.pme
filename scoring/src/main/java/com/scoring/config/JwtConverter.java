package com.scoring.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth = super.extractAuthentication(map);
        AccessTokenDetails details = new AccessTokenDetails();

        if(map.get("userId") != null){
            details.setUserId(Long.valueOf((Integer)map.get("userId")));
        }
        if(map.get("entrepriseId") != null){
            details.setEntrepriseId(Long.valueOf((Integer)map.get("entrepriseId")));
        }
        if(map.get("user_name") != null){
            details.setUsername((String)map.get("user_name"));
        }
        
        if(map.get("profilId") != null){
            details.setIdProfil((Integer)(map.get("profilId")));
        }

        auth.setDetails(details);
        return  auth;   //super.extractAuthentication(map);
    }
}
