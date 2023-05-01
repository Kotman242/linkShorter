package com.example.linkshorter.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JsonTest
public class LinkToTest {

    @Autowired
    private JacksonTester<LinkTO> json;

    @Test
    void testTester() {
        assertNotNull(json);
    }

    @Test
    void testSerializeLinkTO() throws IOException {
        LinkTO link = new LinkTO("Link");
        JsonContent<LinkTO> result = this.json.write(link);
        assertNotNull(result);
        assertEquals(result.getJson(), "{\"link\":\"Link\"}");
    }

    @Test
    void testDeserializeLinkTO() throws IOException {
        LinkTO expected = new LinkTO("Link");
        LinkTO result = this.json.parseObject("{\"link\":\"Link\"}");

        assertNotNull(result);
        assertEquals(result, expected);
    }
}
