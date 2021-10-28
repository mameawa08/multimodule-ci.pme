package com.administration.exception;

public class CalibrageException extends Exception{
	
	/**
	 * 
	 */
	
	  public CalibrageException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public CalibrageException(String message){
		super(message);
	}
	
	public CalibrageException(String message, Throwable e){
		super(message, e);
	}
}
