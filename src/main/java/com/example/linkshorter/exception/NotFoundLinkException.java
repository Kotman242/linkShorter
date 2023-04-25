package com.example.linkshorter.exception;

public class NotFoundLinkException extends RuntimeException {
    public NotFoundLinkException() {
        super();
    }

    public NotFoundLinkException(String message) {
        super(message);
    }
}
