package com.example.linkshorter.repository;

import com.example.linkshorter.model.Link;

public interface LinkRepository {

    void addLink(Link link);
    boolean linkExistShortLink(String link);
    boolean linkExistLongLink(String link);
    Link getLinkWithShortLink(String shortLink);
    Link getLinkWithLongtLink(String shortLink);
    void deleteOldLink(String daySaveLink);
}
