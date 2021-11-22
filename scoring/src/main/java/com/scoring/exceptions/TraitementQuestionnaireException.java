package com.scoring.exceptions;

public class TraitementQuestionnaireException extends Exception {

    public TraitementQuestionnaireException() {
    }

    public TraitementQuestionnaireException(String message) {
        super(message);
    }

    public TraitementQuestionnaireException(String message, Throwable cause) {
        super(message, cause);
    }

    public TraitementQuestionnaireException(Throwable cause) {
        super(cause);
    }

    public TraitementQuestionnaireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
