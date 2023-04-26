package com.example.linkshorter.controller;


import com.example.linkshorter.service.ShortLinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(LinksShorterController.class)
public class linkControllerTest {
    @MockBean
    private ShortLinkService shortLinkService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private static String linkLong;
    private static String linkShort;

    @BeforeAll
    public static void initLink() {
        linkShort = "shortLink";
        linkLong = "https://www.longLink.com/";
    }

    @Test
    void initTest() {
        assertNotNull(linkLong);
        assertNotNull(linkShort);
        assertNotNull(objectMapper);
        assertNotNull(mockMvc);
        assertNotNull(shortLinkService);
    }

    @Test
    void createShortLink_EmptyLink_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createShortLink_CorrectLink_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(linkLong)))
                .andExpect(status().isOk());
    }

    @Test
    void correctSerializationLinkTest() throws Exception {
        when(shortLinkService.createShortLink(anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(linkLong)))
                .andExpect(jsonPath("$.link").isNotEmpty())
                .andExpect(jsonPath("$.link").value(linkShort));
    }

    @Test
    void createShortLink_BadRequestException_Test() throws Exception {
        when(shortLinkService.createShortLink(anyString())).thenReturn(linkShort);

        mockMvc.perform(post("/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(linkShort)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void redirect_CorrectLink_Test() throws Exception {
        when(shortLinkService.getLongLink(linkShort)).thenReturn(linkLong);

        mockMvc.perform(get("/{link}", linkShort))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(linkLong));
    }

    @Test
    void redirect_NotExistsLink_Test() throws Exception {
        when(shortLinkService.getLongLink(linkShort)).thenReturn(linkLong);

        mockMvc.perform(get("/{link}", ""))
                .andExpect(status().isNotFound())
                .andExpect(redirectedUrl(null));
    }

}
