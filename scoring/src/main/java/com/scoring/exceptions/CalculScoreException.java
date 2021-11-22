package com.scoring.exceptions;

public class CalculScoreException extends Exception {

    public CalculScoreException() {
    }

    public CalculScoreException(String message) {
        super(message);
    }

    public CalculScoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculScoreException(Throwable cause) {
        super(cause);
    }

    public CalculScoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
