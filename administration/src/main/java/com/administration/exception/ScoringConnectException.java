package com.administration.exception;

public class ScoringConnectException extends Exception {
    public ScoringConnectException() {
        super();
    }

    public ScoringConnectException(String message) {
        super(message);
    }

    public ScoringConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoringConnectException(Throwable cause) {
        super(cause);
    }

    protected ScoringConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
