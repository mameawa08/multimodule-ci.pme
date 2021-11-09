package com.scoring.exceptions;

public class EntrepriseException extends Exception{

	public EntrepriseException() {
	}

	public EntrepriseException(String message) {
		super(message);
	}

	public EntrepriseException(Throwable cause) {
		super(cause);
	}

	public EntrepriseException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntrepriseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	
	
}
