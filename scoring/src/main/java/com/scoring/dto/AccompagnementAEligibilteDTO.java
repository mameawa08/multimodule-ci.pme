package com.scoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccompagnementAEligibilteDTO {

    private Long id;

    private Long questionEligibilite;

    private String accompagnement;

    private DemandeAccompagnementDTO demandeAccompagnement;

}
