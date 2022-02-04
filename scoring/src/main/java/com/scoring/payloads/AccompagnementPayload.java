package com.scoring.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AccompagnementPayload {

    private Long id;

    private Long idDemandeAccompagnement;

    List<ReponseAccompagnement> reponses;

}
