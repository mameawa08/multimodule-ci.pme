package com.administration.exception;

public class ProfilException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfilException(String message){
		super(message);
	}
	
	public ProfilException(String message, Throwable e){
		super(message, e);
	}
}
