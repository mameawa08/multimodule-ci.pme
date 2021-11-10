package com.scoring.exceptions;

public class EntrepriseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555443288020718722L;

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
