package com.example.linkshorter.service;

import com.example.linkshorter.exception.NotFoundLinkException;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.repository.PersonRepository;
import com.example.linkshorter.validation.CheckerOldLink;
import com.example.linkshorter.validation.ShortLinkMaker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortLinkServiceTest {
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private ShortLinkMaker shortLinkMaker;
    @Mock
    private CheckerOldLink checkerOldLink;

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private ShortLinkService service;

    private static Link link;

    @BeforeAll
    static void start() {
        link = Link.builder()
                .longLink("longLink")
                .shortLink("shortLink")
                .date(new Date())
                .id(1L)
                .build();

    }

    @Test
    void initTest() {
        assertNotNull(linkRepository);
        assertNotNull(shortLinkMaker);
        assertNotNull(checkerOldLink);
        assertNotNull(service);
        assertNotNull(link);
        assertNotNull(personRepository);
    }

    @Test
    void createShortLink_linkAlreadyExistsTest() {
        String longLink = "long";
        String shortLink = "short";
        lenient().when(linkRepository.existsLinkByLongLink(longLink))
                .thenReturn(true);
        lenient().when(shortLinkMaker.getUniqueLineForLink()).thenReturn(shortLink);
        lenient().when((shortLinkMaker.getShortLink(shortLink)))
                .thenReturn(link.getShortLink());
        lenient().when(linkRepository.existsLinkByLongLinkAndPerson(anyString(), any()))
                .thenReturn(false);

        String expected = service.createShortLink(link.getShortLink(), "user1");

        assertNotNull(expected);
        assertEquals(expected, link.getShortLink());
    }

    @Test
    void createShortLink_linkNotExistsTest() {
        lenient().when(linkRepository.existsLinkByLongLink("longLink"))
                .thenReturn(false);
        lenient().when(linkRepository.existsLinkByLongLinkAndPerson(anyString(), any()))
                .thenReturn(false);
        lenient().when(shortLinkMaker.getUniqueLineForLink())
                .thenReturn("uniqueLine");
        lenient().when(shortLinkMaker.getShortLink(anyString()))
                .thenReturn("uniqueLine");

        String expected = service.createShortLink(link.getLongLink(), "user1");
        assertNotNull(expected);
        assertEquals(expected, "uniqueLine");
    }

    @Test
    void getLongLink_LinkNotNull() {
        when(linkRepository.getLinkByShortLink(anyString()))
                .thenReturn(link);

        String result = service.getLongLink(link.getShortLink());

        assertNotNull(result);
        assertEquals(result, link.getLongLink());
    }

    @Test
    void getLongLink_LinkIsNull() {
        when(linkRepository.getLinkByShortLink(anyString()))
                .thenReturn(null);

        Throwable expected = assertThrows(NotFoundLinkException.class, () -> service.getLongLink(link.getShortLink()));

        assertNotNull(expected);
        assertTrue(expected instanceof NotFoundLinkException);
        assertEquals(expected.getMessage(), "Ссылка не найдена");
    }

    @Test
    void getAllLinkTest() {
        List<Link> expected = new ArrayList<>();
        expected.add(Link.builder()
                .shortLink("short")
                .longLink("long")
                .build());
        Person person = new Person();
        when(personRepository.getByUsername("user1")).thenReturn(person);
        when(linkRepository.getLinksByPerson(person)).thenReturn(expected);

        List<Link> result = service.getAllLink("user1");

        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        System.out.println(result.get(0).getShortLink());
        System.out.println("/get/" + expected.get(0).getShortLink());
    }
}

