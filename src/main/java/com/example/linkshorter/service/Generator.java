package com.example.linkshorter.service;

import com.example.linkshorter.model.Link;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Generator {

    @Value("${shortLink.length}")
    private int length;
    @Value("${shortLink.hasNumbers}")
    private boolean hasNumbers;
    @Value("${shortLink.hasLetters}")
    private boolean hasLetters;
    public String createShotLineForLink(){
        return RandomStringUtils.random(length,hasLetters,hasNumbers);
    }
}
