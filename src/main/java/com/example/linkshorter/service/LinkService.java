package com.example.linkshorter.service;

public interface LinkService {

    String createShortLink(String longLink);

    String getLongLink(String shortLink);
}
