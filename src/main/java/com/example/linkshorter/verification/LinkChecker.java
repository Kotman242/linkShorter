package com.example.linkshorter.verification;

import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.service.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkChecker {

    private final Generator generator;
    private final LinkRepository linkRepository;

    public String getUniquelink(){
        String link = generator.createShotLink();
        while (linkRepository.linkExistShortLink(link)){
            link = generator.createShotLink();
        }
        return link;
    }
}
