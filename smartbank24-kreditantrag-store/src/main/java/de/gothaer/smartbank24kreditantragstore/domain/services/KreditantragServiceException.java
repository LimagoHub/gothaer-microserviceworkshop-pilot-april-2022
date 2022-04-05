package de.gothaer.smartbank24kreditantragstore.domain.services;

public class KreditantragServiceException extends RuntimeException {
    public KreditantragServiceException() {
    }

    public KreditantragServiceException(String message) {
        super(message);
    }

    public KreditantragServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public KreditantragServiceException(Throwable cause) {
        super(cause);
    }

    public KreditantragServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
