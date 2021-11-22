package com.scoring.exceptions;

public class ScoreEntrepriseParParametreException extends Exception {

    public ScoreEntrepriseParParametreException() {
    }

    public ScoreEntrepriseParParametreException(String message) {
        super(message);
    }

    public ScoreEntrepriseParParametreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoreEntrepriseParParametreException(Throwable cause) {
        super(cause);
    }

    public ScoreEntrepriseParParametreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
