package com.administration.exception;

public class RatioException extends Exception{
	
	/**
	 * 
	 */
	
	  public RatioException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public RatioException(String message){
		super(message);
	}
	
	public RatioException(String message, Throwable e){
		super(message, e);
	}
}
