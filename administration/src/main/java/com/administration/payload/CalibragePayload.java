package com.administration.payload;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalibragePayload {
	
	private Long 			id;
	private BigDecimal 		min;
	private BigDecimal 		max;
	private int 			classe;
	private Long 			idRatio;
}
