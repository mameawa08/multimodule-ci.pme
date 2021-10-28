package com.administration.exception;

public class ParametreException extends Exception{
	
	/**
	 * 
	 */
	
	  public ParametreException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public ParametreException(String message){
		super(message);
	}
	
	public ParametreException(String message, Throwable e){
		super(message, e);
	}
}
