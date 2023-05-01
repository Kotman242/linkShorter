package com.example.linkshorter.service;

import com.example.linkshorter.exception.NotFoundLinkException;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.repository.PersonRepository;
import com.example.linkshorter.validation.CheckerOldLink;
import com.example.linkshorter.validation.ShortLinkMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortLinkService implements LinkService {

    private final LinkRepository linkRepository;
    private final PersonRepository personRepository;
    private final ShortLinkMaker shortLinkMaker;
    private final CheckerOldLink checkerOldLink;

    @Override
    public String createShortLink(String longLink, String userLogin) {
        checkerOldLink.checkOldLink();
        Person person = personRepository.getByUsername(userLogin);
        if (linkRepository.existsLinkByLongLinkAndPerson(longLink, person)) {
            return shortLinkMaker.getShortLink(linkRepository.getFirstLinkByLongLink(longLink).getShortLink());
        } else if (userLogin.equals("anonymousUser") && linkRepository.existsLinkByLongLink(longLink)) {
            return shortLinkMaker.getShortLink(linkRepository.getFirstLinkByLongLink(longLink).getShortLink());
        }
        String shortLink = shortLinkMaker.getUniqueLineForLink();
        Link link;
        if (userLogin.equals("anonymousUser")) {
            link = Link.builder()
                    .longLink(longLink)
                    .shortLink(shortLink)
                    .date(new Date())
                    .build();
        } else {
            link = Link.builder()
                    .longLink(longLink)
                    .shortLink(shortLink)
                    .date(new Date())
                    .person(person)
                    .build();
        }
        linkRepository.save(link);
        return shortLinkMaker.getShortLink(link.getShortLink());

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

    @Override
    public List<Link> getAllLink(String login) {
        return linkRepository.getLinksByPerson(personRepository.getByUsername(login));
    }
}
