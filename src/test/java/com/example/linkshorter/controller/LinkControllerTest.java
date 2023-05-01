package com.example.linkshorter.controller;


import com.example.linkshorter.model.Link;
import com.example.linkshorter.model.Person;
import com.example.linkshorter.service.ShortLinkService;
import com.example.linkshorter.unitl.PersonHttpBasicTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LinkControllerTest {
    @MockBean
    private ShortLinkService shortLinkService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private static String linkLong;
    private static String linkShort;
    private static Person person;

    @BeforeAll
    public static void initLink() {
        linkShort = "shortLink";
        linkLong = "https://www.longLink.com/";
        person = Person.builder()
                .username("user1")
                .password("11111")
                .build();
    }

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    void initTest() {
        assertNotNull(linkLong);
        assertNotNull(linkShort);
        assertNotNull(mockMvc);
        assertNotNull(shortLinkService);
        assertNotNull(person);
        assertNotNull(webApplicationContext);
    }

    @Test
    void createShortLink_EmptyLink_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString(), anyString())).thenReturn(linkShort);

        assertThrows(IllegalArgumentException.class, () -> mockMvc.perform(post("/generate")
                .with(csrf())
                .with(PersonHttpBasicTest.personHttpBasic(person))
                .param("")));
    }

    @Test
    void createShortLink_CorrectLinkAnonymous_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString(), anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .with(csrf())
                        .with(anonymous())
                        .param("link", linkLong)
                        .with(PersonHttpBasicTest.personHttpBasic(person))
                )
                .andExpect(status().isOk());
    }

    @Test
    void createShortLink_CorrectLink_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString(), anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .with(csrf())
                        .with(PersonHttpBasicTest.personHttpBasic(person))
                        .param("link", linkLong)
                )
                .andExpect(status().isOk());
    }


    @Test
    void createShortLink_BadRequestException_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString(), anyString())).thenReturn(linkShort);
        assertThrows(IllegalArgumentException.class, () -> mockMvc.perform(post("/generate")
                .with(csrf())
                .with(PersonHttpBasicTest.personHttpBasic(person))
                .param(linkShort)));
    }

    @Test
    void redirect_CorrectLink_Test() throws Exception {
        when(shortLinkService.getLongLink(linkShort)).thenReturn(linkLong);

        mockMvc.perform(get("/get/{link}", linkShort))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(linkLong));
    }

    @Test
    void redirect_NotExistsLink_Test() throws Exception {
        when(shortLinkService.getLongLink(linkShort)).thenReturn(linkLong);

        // assertThrows(IllegalArgumentException.class,()-> mockMvc.perform(get("/{link}", "")));
        mockMvc.perform(get("/get/{link}", ""))
                .andExpect(status().isNotFound())
                .andExpect(redirectedUrl(null));
    }

    @Test
    void redirect_NotExistsLinkAnonymous_Test() throws Exception {
        when(shortLinkService.getLongLink(linkShort)).thenReturn(linkLong);

        // assertThrows(IllegalArgumentException.class,()-> mockMvc.perform(get("/{link}", "")));
        mockMvc.perform(get("/get/{link}", ""))
                .andExpect(status().isNotFound())
                .andExpect(redirectedUrl(null));
    }

    @Test
    void getAllLinkTest() throws Exception {
        List<Link> expected = List.of(Link.builder()
                .longLink(linkLong)
                .shortLink(linkShort)
                .build());

        when(shortLinkService.getAllLink(anyString())).thenReturn(expected);
        mockMvc.perform(get("/all")
                        .with(csrf())
                        .with(PersonHttpBasicTest.personHttpBasic(person))
                )
                .andExpect(status().isOk());
    }

    @Test
    void getAllLinkAnonymousTest() throws Exception {
        List<Link> expected = List.of(Link.builder()
                .longLink(linkLong)
                .shortLink(linkShort)
                .build());

        when(shortLinkService.getAllLink(anyString())).thenReturn(expected);
        mockMvc.perform(get("/all")
                        .with(csrf())
                        .with(anonymous())
                )
                .andExpect(status().isUnauthorized());
    }

}
