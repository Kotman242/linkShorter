package com.example.linkshorter.ex;

public class NotFoundLincEx extends RuntimeException{
    public NotFoundLincEx() {
        super();
    }

    public NotFoundLincEx(String message) {
        super(message);
    }
}
