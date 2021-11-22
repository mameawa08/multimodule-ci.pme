package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionnaireQualitatifPayload {

	private Long idEntreprise;
	
	List<ReponseQualitativePayload> listReponse;
}
