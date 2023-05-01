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
public class LinkTest {


    @Autowired
    private JacksonTester<Link> json;

    private static Link link;

    @BeforeAll
    static void init() {
        link = Link.builder()
                .shortLink("short")
                .longLink("long")
                .id(1L)
                .date(null)
                .build();
    }

    @Test
    void testTester() {
        assertNotNull(json);
        assertNotNull(link);
    }

    @Test
    void testSerializePerson() throws IOException {
        JsonContent<Link> result = this.json.write(link);
        assertNotNull(result);
        assertEquals(result.getJson(), "{\"id\":1," +
                "\"shortLink\":\"short\"" +
                ",\"longLink\":\"long\"" +
                ",\"date\":null" +
                ",\"person\":null}");
    }

    @Test
    void testDeserializePerson() throws IOException {
        Link result = this.json.parseObject("{\"id\":1," +
                "\"shortLink\":\"short\"" +
                ",\"longLink\":\"long\"" +
                ",\"date\":null" +
                ",\"person\":null}");

        assertNotNull(result);
        assertEquals(result, link);
    }
}
