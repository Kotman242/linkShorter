package com.example.linkshorter.service;

import com.example.linkshorter.exception.NotFoundLinkException;
import com.example.linkshorter.model.Link;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.verification.CheckerOldLink;
import com.example.linkshorter.verification.ShortLinkMaker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ShortLinkServiceTest {

    @MockBean
    private LinkRepository linkRepository;
    @MockBean
    private ShortLinkMaker shortLinkMaker;
    @MockBean
    private CheckerOldLink checkerOldLink;

    @Autowired
    private ShortLinkService service;

    private static Link link;

    @BeforeAll
    static void start() {
        link = new Link(1, "shortLink", "longLink", new Date());
    }

    @Test
    void initTest() {
        assertNotNull(linkRepository);
        assertNotNull(shortLinkMaker);
        assertNotNull(checkerOldLink);
        assertNotNull(service);
        assertNotNull(link);
    }

    @Test
    void createShortLink_linkAlreadyExistsTest() {
        when(linkRepository.existsLinkByLongLink(anyString()))
                .thenReturn(true);
        when(linkRepository.getLinkByLongLink(anyString()))
                .thenReturn(link);
        when((shortLinkMaker.getShortLink(anyString())))
                .thenReturn(link.getShortLink());

        String expected = service.createShortLink(link.getShortLink());

        assertNotNull(expected);
        assertEquals(expected, link.getShortLink());
    }

    @Test
    void createShortLink_linkNotExistsTest() {
        when(linkRepository.existsLinkByLongLink("longLink"))
                .thenReturn(false);
        when(shortLinkMaker.getUniqueLineForLink())
                .thenReturn("uniqueLine");
        lenient().when(shortLinkMaker.getShortLink(anyString()))
                .thenReturn("uniqueLine");

        String expected = service.createShortLink(link.getLongLink());
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

}

