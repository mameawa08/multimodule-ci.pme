package com.scoring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * @author agileway
 */

@Setter
@Getter
public class ScoresAndRatioDTO {

    private Long id;
	
	private ScoresParPMEDTO scoreDTO;
    
    private List<ValeurRatioDTO> listValeurRatioDTO;

}
