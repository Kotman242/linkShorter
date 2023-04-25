package com.example.linkshorter.service;

import com.example.linkshorter.exception.NotFoundLinkException;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.verification.CheckerOldLink;
import com.example.linkshorter.verification.ShortLinkMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortLinkService implements LinkService {

    private final LinkRepository linkRepository;
    private final ShortLinkMaker shortLinkMaker;
    private final CheckerOldLink checkerOldLink;

    @Override
    public String createShortLink(String longLink) {
        if (linkRepository.existsLinkByLongLink(longLink)) {
            return shortLinkMaker.getShortLink(linkRepository.getLinkByLongLink(longLink).getShortLink());
        }
        String shortLink = shortLinkMaker.getUniqueLineForLink();
        Link link = Link.builder()
                .longLink(longLink)
                .shortLink(shortLink)
                .build();
        linkRepository.save(link);
        String result = shortLinkMaker.getShortLink(link.getShortLink());
        return result;
    }

    @Override
    public String getLongLink(String shortLink) {
        checkerOldLink.checkOldLink();
        Link link = linkRepository.getLinkByShortLink(shortLink);
        if (link == null) {
            throw new NotFoundLinkException("Ссылка не найдена");
        }
        return link.getLongLink();
    }


}
