package com.scoring.exceptions;

public class FileGenerationException extends Exception {
    public FileGenerationException() {
    }

    public FileGenerationException(String message) {
        super(message);
    }

    public FileGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileGenerationException(Throwable cause) {
        super(cause);
    }

    public FileGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
