package com.administration.exception;

public class ProfilException extends Exception{
	
	public ProfilException(String message){
		super(message);
	}
	
	public ProfilException(String message, Throwable e){
		super(message, e);
	}
}
