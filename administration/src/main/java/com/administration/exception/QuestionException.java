package com.administration.exception;

public class QuestionException extends Exception{
	
	/**
	 * 
	 */
	
	  public QuestionException() {
	        super();
	    }
	
	private static final long serialVersionUID = 1L;

	public QuestionException(String message){
		super(message);
	}
	
	public QuestionException(String message, Throwable e){
		super(message, e);
	}
}
