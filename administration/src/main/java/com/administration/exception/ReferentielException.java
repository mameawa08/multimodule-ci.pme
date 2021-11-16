package com.administration.exception;

public class ReferentielException extends Exception{
    public ReferentielException() {
        super();
    }

    public ReferentielException(String message) {
        super(message);
    }

    public ReferentielException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferentielException(Throwable cause) {
        super(cause);
    }

    protected ReferentielException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
