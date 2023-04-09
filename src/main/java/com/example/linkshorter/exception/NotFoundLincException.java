package com.example.linkshorter.exception;

public class NotFoundLincException extends RuntimeException{
    public NotFoundLincException() {
        super();
    }

    public NotFoundLincException(String message) {
        super(message);
    }
}
