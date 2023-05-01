package com.example.linkshorter.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonTest
public class PersonTest {

    @Autowired
    private JacksonTester<Person> json;

    private static Person person;

    @BeforeAll
    static void init() {
        person = Person.builder()
                .id(1L)
                .username("user")
                .password("11111").build();
    }

    @Test
    void testTester() {
        assertNotNull(json);
        assertNotNull(person);
    }

    @Test
    void testSerializePerson() throws IOException {
        JsonContent<Person> result = this.json.write(person);
        assertNotNull(result);
        assertEquals(result.getJson(), "{\"id\":1,\"username\":\"user\",\"password\":\"11111\"}");
    }

    @Test
    void testDeserializePerson() throws IOException {
        Person result = this.json.parseObject("{\"id\":1,\"username\":\"user\",\"password\":\"11111\"}");

        assertNotNull(result);
        assertEquals(result, person);
    }
}
