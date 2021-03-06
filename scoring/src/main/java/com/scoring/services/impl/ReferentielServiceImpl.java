package com.scoring.services.impl;

import java.math.BigDecimal;
import java.util.List;

import com.scoring.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scoring.exceptions.ReferentielException;
import com.scoring.services.IReferentielService;

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
    
    @Override
    public List<RatioDTO> getlisteRatios() throws Exception {
		ResponseEntity<List<RatioDTO>> resp = 
				rt.exchange(baseUrl+"/ratios", HttpMethod.GET, null, new ParameterizedTypeReference<List<RatioDTO>>() {});
		return resp.getBody();
	}
		
    @Override
	public CalibrageDTO getCalibrageByRatioAndValeurCalcule(Long idRatio, BigDecimal valeurRatio) throws Exception {
		ResponseEntity<CalibrageDTO> resp = 
				rt.exchange(baseUrl+"/calibrages/ratio/"+idRatio+"/"+valeurRatio, HttpMethod.GET, null, CalibrageDTO.class);
		return resp.getBody();
	}
    
    @Override
	public RatioDTO getRatioById(Long idRatio) throws Exception {
		ResponseEntity<RatioDTO> resp = 
				rt.exchange(baseUrl+"/ratios/"+idRatio, HttpMethod.GET, null, RatioDTO.class);
		return resp.getBody();
	}
    
    @Override
   	public QuestionDTO getQuestionById(Long idQuestion) throws Exception {
        HttpHeaders headers = getHttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

   		ResponseEntity<QuestionDTO> resp = 
   				rt.exchange(baseUrl+"/questions/"+idQuestion, HttpMethod.GET, entity, QuestionDTO.class);
   		return resp.getBody();
   	}


   	@Override
   	public List<PonderationDTO> getPonderations() throws ReferentielException{
        HttpHeaders headers = getHttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<PonderationDTO>> resp = rt.exchange(baseUrl + "/ponderations", HttpMethod.GET, entity, new ParameterizedTypeReference<List<PonderationDTO>>() {});
        return resp.getBody();
    }

    @Override
    public List<RatioDTO> getRatios() throws ReferentielException {
        HttpHeaders headers = getHttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<RatioDTO>> resp =
                rt.exchange(baseUrl + "/ratios", HttpMethod.GET, entity, new ParameterizedTypeReference<List<RatioDTO>>() {});
        return resp.getBody();

    }

    @Override
    public ParametreDTO getParamtre(Long id) throws ReferentielException{
        HttpHeaders headers = getHttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<ParametreDTO> resp =
                rt.exchange(baseUrl + "/parametres/"+id, HttpMethod.GET, entity, new ParameterizedTypeReference<ParametreDTO>() {});
        return resp.getBody();
    }

    @Override
    public List<ParametreDTO> getParamtres() throws ReferentielException{
        HttpHeaders headers = getHttpHeaders();

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<ParametreDTO>> resp =
                rt.exchange(baseUrl + "/parametres", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ParametreDTO>>() {});
        return resp.getBody();
    }

    @Override
   	public PonderationDTO getPonderationByParametre(Long idParametre) throws Exception {
   		ResponseEntity<PonderationDTO> resp =
   				rt.exchange(baseUrl+"/ponderations/parametre/"+idParametre, HttpMethod.GET, null, PonderationDTO.class);
   		return resp.getBody();
   	}

    @Override
   	public PonderationDTO getPonderationScoreFinancier() throws Exception {
   		ResponseEntity<PonderationDTO> resp =
   				rt.exchange(baseUrl+"/ponderations/score/financier", HttpMethod.GET, null, PonderationDTO.class);
   		return resp.getBody();
   	}


    private HttpHeaders getHttpHeaders() {
        OAuth2AuthenticationDetails principal = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer "+principal.getTokenValue());
        return headers;
    }
}
