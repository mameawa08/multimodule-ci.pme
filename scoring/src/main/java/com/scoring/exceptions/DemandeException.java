package com.scoring.exceptions;

public class DemandeException extends Exception{
    public DemandeException() {
    }

    public DemandeException(String message) {
        super(message);
    }

    public DemandeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemandeException(Throwable cause) {
        super(cause);
    }

    public DemandeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
