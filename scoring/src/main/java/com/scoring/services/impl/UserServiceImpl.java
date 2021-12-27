package com.scoring.services.impl;


import com.scoring.config.AccessTokenDetails;
import com.scoring.dto.UserDTO;
import com.scoring.exceptions.UserException;
import com.scoring.payloads.AddEntreprisePayload;
import com.scoring.services.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private RestTemplate rt;

    @Value("${app.administration.url}")
    private String baseUrl;

    public UserServiceImpl() {
        rt = new RestTemplate();
    }

    @Override
    public UserDTO addEntrepriseToUser(Long userId, Long entrepriseId) throws UserException{
        String url = baseUrl+"/auth/users/"+userId+"/entreprises";

        AddEntreprisePayload payload = new AddEntreprisePayload();
        payload.setEntrepriseId(entrepriseId.intValue());
        payload.setUserId(userId);

        HttpHeaders headers = getHttpHeaders();

        HttpEntity<AddEntreprisePayload> request = new HttpEntity<>(payload, headers);

        ResponseEntity<UserDTO> resp = rt.exchange(url, HttpMethod.PUT, request, UserDTO.class);
        if (resp.getBody() != null)
            return  resp.getBody();
        else
            throw new UserException("Erreur lors de l'ajout de l'entreprise.");
    }



    @Override
    public AccessTokenDetails getConnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            AccessTokenDetails accessTokenDetails = (AccessTokenDetails) details.getDecodedDetails();
            return accessTokenDetails;
        }
        return null;
    }

    @Override
    public List<UserDTO> getUsersByProfil(Long id) throws UserException{
        HttpHeaders headers = getHttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<UserDTO>> resp = rt.exchange(baseUrl + "/auth/users/profils/" + id, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserDTO>>(){});
        return  resp.getBody();
    }

//    Private Methods
    private HttpHeaders getHttpHeaders() {
        OAuth2AuthenticationDetails principal = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer "+principal.getTokenValue());
        return headers;
    }
}
