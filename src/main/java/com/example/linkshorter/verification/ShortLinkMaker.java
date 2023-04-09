package com.example.linkshorter.verification;

import com.example.linkshorter.model.Link;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.service.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShortLinkMaker {

    private final Generator generator;
    private final LinkRepository linkRepository;

    @Value("${shortLink.host}")
    private String host;
    public String getUniqueLineForLink(){
        String link = generator.createShotLineForLink();
        while (linkRepository.linkExistShortLink(link)){
            link = generator.createShotLineForLink();
        }
        return link;
    }

    public String getShortLink(Link link){
        return host+"/"+link.getShortLink();
    }
}
