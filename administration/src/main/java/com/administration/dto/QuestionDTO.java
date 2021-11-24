package com.administration.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class QuestionDTO {

    private Long id;
	
	private String code;

    private String libelle;
    
    private String libelleTheme;
    
	private int	actif;
    
    private ParametreDTO  parametreDTO;
    
    private List<ReponseQualitativeDTO> listReponsesDTO;

}
