package com.administration.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class CalibrageDTO {

    private Long id;
	
	private double min;

    private double max;
    
    private int classe;
    
	private int	actif;

}
