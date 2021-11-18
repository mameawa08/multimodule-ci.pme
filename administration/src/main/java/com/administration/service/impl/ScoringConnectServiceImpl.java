package com.administration.service.impl;

import com.administration.dto.EntrepriseDTO;
import com.administration.exception.ScoringConnectException;
import com.administration.service.IScoringConnectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoringConnectServiceImpl implements IScoringConnectService {

    private final RestTemplate rt;

    @Value("${app.scoring.url}")
    private String baseUrl;

    public ScoringConnectServiceImpl() {
        rt = new  RestTemplate();
    }

    @Override
    public Long getEntreprise(Long id) throws ScoringConnectException{
        String url = baseUrl+"/scoring/api/entreprises/"+id;

        HttpHeaders headers = getHttpHeaders();

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<EntrepriseDTO> resp = rt.exchange(url, HttpMethod.GET, httpEntity, EntrepriseDTO.class);
        if(resp.getBody() == null){
            throw new ScoringConnectException("Entreprise :: not found.");
        }
        else{
            return resp.getBody().getId();
        }
    }

    private HttpHeaders getHttpHeaders() {
        OAuth2AuthenticationDetails principal = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer "+principal.getTokenValue());
        return headers;
    }
}
