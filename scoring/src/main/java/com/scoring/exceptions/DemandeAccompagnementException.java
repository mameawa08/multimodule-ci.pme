package com.scoring.exceptions;

public class DemandeAccompagnementException extends Exception {

    public DemandeAccompagnementException() {
        super();
    }

    public DemandeAccompagnementException(String message) {
        super(message);
    }

    public DemandeAccompagnementException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemandeAccompagnementException(Throwable cause) {
        super(cause);
    }

    protected DemandeAccompagnementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
