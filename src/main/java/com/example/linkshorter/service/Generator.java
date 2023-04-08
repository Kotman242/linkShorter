package com.example.linkshorter.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Generator {

    @Value("${generator.link.length}")
    private int length;
    @Value("${generator.link.hasNumbers}")
    private boolean hasNumbers;
    @Value("${generator.link.hasLetters}")
    private boolean hasLetters;
    public String createShotLink(){
        return RandomStringUtils.random(length,hasLetters,hasNumbers);
    }
}
