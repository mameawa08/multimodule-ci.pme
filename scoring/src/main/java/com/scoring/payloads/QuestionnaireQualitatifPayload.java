package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionnaireQualitatifPayload {

	private Long idEntreprise;
	
	private Long idDemande;
	
	List<ReponseQualitativePayload> listReponse;
}
