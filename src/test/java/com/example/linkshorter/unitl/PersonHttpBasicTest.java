package com.example.linkshorter.unitl;

import com.example.linkshorter.model.Person;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public class PersonHttpBasicTest {

    public static RequestPostProcessor personHttpBasic(Person person) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(person.getUsername(), person.getPassword());
    }
}
