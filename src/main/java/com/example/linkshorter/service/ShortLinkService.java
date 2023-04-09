package com.example.linkshorter.service;

import com.example.linkshorter.exception.NotFoundLincException;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.verification.CheckerOldLink;
import com.example.linkshorter.verification.ShortLinkMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortLinkService implements LinkService{

    private final LinkRepository linkRepository;
    private final ShortLinkMaker shortLinkMaker;
    private final CheckerOldLink checkerOldLink;
    @Override
    public String createShortLink(String longLink){
        if(linkRepository.linkExistLongLink(longLink)){
            return linkRepository.getLinkWithLongtLink(longLink).getShortLink();
        }
        String shortLink = shortLinkMaker.getUniqueLineForLink();
        Link link = new Link(shortLink,longLink);
        linkRepository.addLink(link);
        return shortLinkMaker.getShortLink(link);
    }
    @Override
    public String getLongLink(String shortLink){
        checkerOldLink.checkOldLink();
        Link link = linkRepository.getLinkWithShortLink(shortLink);
        if (link ==null){
            throw new NotFoundLincException("Ссылка не найдена");
        }
        return link.getLongLink();
    }


}
