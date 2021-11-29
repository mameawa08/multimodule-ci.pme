package com.scoring.exceptions;

public class ReportPrinterException extends Exception {

    public ReportPrinterException() {
    }

    public ReportPrinterException(String message) {
        super(message);
    }

    public ReportPrinterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportPrinterException(Throwable cause) {
        super(cause);
    }

    public ReportPrinterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
