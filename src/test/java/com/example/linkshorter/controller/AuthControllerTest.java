package com.example.linkshorter.controller;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.service.RegistrationService;
import com.example.linkshorter.unitl.PersonHttpBasicTest;
import com.example.linkshorter.validation.PersonValidation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @MockBean
    private PersonValidation validation;
    @MockBean
    private RegistrationService service;
    @InjectMocks
    private AuthController authController;
    private static Person person;

    @BeforeAll
    public static void initLink() {
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
        assertNotNull(validation);
        assertNotNull(service);
        assertNotNull(mockMvc);
        assertNotNull(authController);
        assertNotNull(webApplicationContext);
        assertNotNull(person);
    }

    @Test
    void authTest() throws Exception {
        mockMvc.perform(get("/")
                        .with(PersonHttpBasicTest.personHttpBasic(person)))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void authAnonymousTest() throws Exception {
        mockMvc.perform(get("/")
                        .with(anonymous()))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void registrationGetTest() throws Exception {
        mockMvc.perform(get("/registration")
                        .with(PersonHttpBasicTest.personHttpBasic(person)))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void registrationGetAnonymousTest() throws Exception {
        mockMvc.perform(get("/registration")
                        .with(anonymous()))
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void registrationPostTest() throws Exception {
        mockMvc.perform(post("/registration")
                        .with(csrf())
                        .with(PersonHttpBasicTest.personHttpBasic(person)))
                .andExpect(status().isFound());
    }

    @Test
    void registrationPostAnonymousTest() throws Exception {
        mockMvc.perform(post("/registration")
                        .with(csrf())
                        .with(anonymous()))
                .andExpect(status().isFound());
    }
}
