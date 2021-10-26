package com.administration.exception;

public class ReponseException extends Exception{
	
	/**
	 * 
	 */
	
	  public ReponseException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public ReponseException(String message){
		super(message);
	}
	
	public ReponseException(String message, Throwable e){
		super(message, e);
	}
}
