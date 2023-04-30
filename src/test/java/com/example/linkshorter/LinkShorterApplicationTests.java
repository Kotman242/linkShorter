package com.example.linkshorter;

import com.example.linkshorter.controller.LinksShorterController;
import com.example.linkshorter.repository.LinkRepository;
import com.example.linkshorter.service.LinkService;
import com.example.linkshorter.validation.CheckerOldLink;
import com.example.linkshorter.validation.Generator;
import com.example.linkshorter.validation.ShortLinkMaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class LinkShorterApplicationTests {

    @Autowired
    private LinksShorterController linksShorterController;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkService linkService;
    @Autowired
    private CheckerOldLink checkerOldLink;
    @Autowired
    private Generator generator;
    @Autowired
    private ShortLinkMaker shortLinkMaker;

    @Test
    void contextLoads() {
        assertNotNull(linksShorterController);
        assertNotNull(linkRepository);
        assertNotNull(linkService);
        assertNotNull(checkerOldLink);
        assertNotNull(generator);
        assertNotNull(shortLinkMaker);
    }

}
