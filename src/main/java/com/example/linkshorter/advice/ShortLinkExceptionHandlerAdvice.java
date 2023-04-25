package com.example.linkshorter.advice;

import com.example.linkshorter.exception.NotFoundLinkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShortLinkExceptionHandlerAdvice {

    @ExceptionHandler(NotFoundLinkException.class)
    public ResponseEntity<String> notFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> badRequestException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getFieldError().getDefaultMessage());
    }
}
