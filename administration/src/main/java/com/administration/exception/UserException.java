package com.administration.exception;

public class UserException extends Exception{
	
	public UserException(String message){
		super(message);
	}
	
	public UserException(String message, Throwable e){
		super(message, e);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
