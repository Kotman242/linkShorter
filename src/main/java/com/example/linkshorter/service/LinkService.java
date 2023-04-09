package com.example.linkshorter.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface LinkService {

    String createShortLink(String longLink);

    String getLongLink(String shortLink);
}
