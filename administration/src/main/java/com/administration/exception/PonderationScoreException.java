package com.administration.exception;

public class PonderationScoreException extends Exception{
	
	/**
	 * 
	 */
	
	  public PonderationScoreException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public PonderationScoreException(String message){
		super(message);
	}
	
	public PonderationScoreException(String message, Throwable e){
		super(message, e);
	}
}
