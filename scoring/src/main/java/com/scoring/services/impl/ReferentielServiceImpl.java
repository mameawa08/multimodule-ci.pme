package com.scoring.services.impl;

import com.scoring.dto.FormeJuridiqueDTO;
import com.scoring.dto.SecteurActiviteDTO;
import com.scoring.exceptions.ReferentielException;
import com.scoring.services.IReferentielService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReferentielServiceImpl implements IReferentielService {

    private RestTemplate rt;

    @Value("${app.administration.url}")
    private String baseUrl;

    public ReferentielServiceImpl() {
        this.rt = new RestTemplate();
    }

    @Override
    public FormeJuridiqueDTO getFormeJuridique(Long id) throws ReferentielException {
        ResponseEntity<FormeJuridiqueDTO> resp = rt.exchange(baseUrl+"/referentiel/formes-juridiques/"+id, HttpMethod.GET, null, FormeJuridiqueDTO.class);
        return resp.getBody();
    }

    @Override
    public SecteurActiviteDTO getSecteurActivite(Long id) throws ReferentielException {
        ResponseEntity<SecteurActiviteDTO> resp = rt.exchange(baseUrl+"/referentiel/secteurs-activites/"+id, HttpMethod.GET, null, SecteurActiviteDTO.class);
        return resp.getBody();
    }
}