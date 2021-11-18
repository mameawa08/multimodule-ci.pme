package com.administration.config.oauth.server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.administration.config.oauth.entities.UserDetailsImpl;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;



public class CustomTokenEnhancer extends JwtAccessTokenConverter {       
	Long profilId = null;
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());
        if (principal.getUser().getId() != null){
            info.put("userId", principal.getUser().getId());
        }
        if(principal.getUser().getEntrepriseId() != null){
            info.put("entrepriseId", principal.getUser().getEntrepriseId());
        }
        if(principal.getUser().getProfil() != null){
            info.put("profilId", principal.getUser().getProfil().getId());
        }
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);
        return super.enhance(customAccessToken, authentication);
    }
}
