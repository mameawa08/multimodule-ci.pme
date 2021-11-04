package com.administration.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalibragePayload {
	
	private Long 			id;
	private double 			min;
	private double 			max;
	private int 			classe;
	private Long 			idRatio;
}
