package com.example.linkshorter.service;

import com.example.linkshorter.model.Link;

import java.util.List;

public interface LinkService {

    String createShortLink(String longLink, String userName);

    String getLongLink(String shortLink);

    List<Link> getAllLink(String login);
}
