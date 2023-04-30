package com.example.linkshorter.validation;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Generator {

    private int length;
    private boolean hasNumbers;
    private boolean hasLetters;

    public Generator(@Value("${token.length}") int length,
                     @Value("${token.hasNumbers}") boolean hasNumbers,
                     @Value("${token.hasLetters}") boolean hasLetters) {
        this.length = length;
        this.hasNumbers = hasNumbers;
        this.hasLetters = hasLetters;
    }

    public String createShotLineForLink() {
        return RandomStringUtils.random(length, hasLetters, hasNumbers);
    }
}
