package com.scoring.payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionnaireEliPayload {

	private Long idEntreprise;
	
	List<ReponseParPMEPayload> listReponse;
}
