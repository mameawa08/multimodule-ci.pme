package com.administration.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author agileway
 */

@Setter
@Getter
public class CalibrageDTO {

    private Long id;
	
	private BigDecimal min;

    private BigDecimal max;
    
    private int classe;
    
	private int	actif;
	
	private RatioDTO ratioDTO;

}
