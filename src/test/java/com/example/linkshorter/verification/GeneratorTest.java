package com.example.linkshorter.verification;

import static org.junit.jupiter.api.Assertions.*;

import com.example.linkshorter.LinkShorterApplication;
import com.example.linkshorter.verification.Generator;
import org.assertj.core.api.junit.jupiter.SoftlyExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@TestPropertySource(value = "classpath:test.properties")
public class GeneratorTest {

    private Generator generator;
    @Test
    void createTokenForLink_hasNumbersAndHasLetters_Test(){
        generator =  new Generator(10,true,true);
        String result = generator.createShotLineForLink();

        assertNotNull(result);
        assertTrue(result.length()==10);
        assertTrue(result.matches(".*[\\d].*"));
        assertTrue(result.matches(".*[a-zA-Z].*"));
    }

    @Test
    void createTokenForLink_HasOnlyLetters_Test(){
        generator =  new Generator(10,false,true);
        String result = generator.createShotLineForLink();

        assertNotNull(result);
        assertTrue(result.length()==10);
        assertFalse(result.matches(".*[\\d].*"));
        assertTrue(result.matches(".*[a-zA-Z].*"));
    }

    @Test
    void createTokenForLink_HasOnlyNumbers_Test(){
        generator =  new Generator(10,true,false);
        String result = generator.createShotLineForLink();

        System.out.println(result);
        assertNotNull(result);
        assertTrue(result.length()==10);
        assertTrue(result.matches(".*[\\d].*"));
        assertFalse(result.matches(".*[a-zA-Z].*"));
    }
}
