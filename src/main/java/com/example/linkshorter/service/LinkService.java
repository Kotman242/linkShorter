package com.example.linkshorter.service;

import com.example.linkshorter.ex.NotFoundLincEx;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.verification.LinkChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    private final LinkChecker linkChecker;
    public String createShortLink(@PathVariable String longLink){
        if(linkRepository.linkExistLongLink(longLink)){
            return linkRepository.getLinkWithLongtLink(longLink).getShortLink();
        }
        String shortLink = linkChecker.getUniquelink();
        Link link = new Link(shortLink,longLink);
        linkRepository.addLink(link);
        return link.getShortLink();
    }

    public String getLongLink(String shortLink){
        Link link = linkRepository.getLinkWithShortLink(shortLink);
        if (link ==null){
            throw new NotFoundLincEx("Ссылка не найдена");
        }
        return link.getLongLink();
    }


}
