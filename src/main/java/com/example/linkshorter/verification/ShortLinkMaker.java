package com.example.linkshorter.verification;

import com.example.linkshorter.repository.LinkRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShortLinkMaker {
    private  Generator generator;
    private LinkRepository linkRepository;
    private String domain;
    @Autowired
    public ShortLinkMaker(Generator generator,
                          LinkRepository linkRepository,
                          @Value("${shortLink.domain}") String domain) {
        this.generator = generator;
        this.linkRepository = linkRepository;
        this.domain = domain;
    }

    public ShortLinkMaker() {
    }

    public String getUniqueLineForLink() {
        String link = generator.createShotLineForLink();
        while (linkRepository.existsLinkByShortLink(link)) {
            link = generator.createShotLineForLink();
        }
        return link;
    }

    public String getShortLink(String link) {
        return domain + "/" + link;
    }
}
