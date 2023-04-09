package com.example.linkshorter.model;

public class Link {
    private String shortLink;
    private String longLink;

    public Link(String shortLink, String longLink) {
        this.shortLink = shortLink;
        this.longLink = longLink;
    }
    public Link() {
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getLongLink() {
        return longLink;
    }

    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }
}
