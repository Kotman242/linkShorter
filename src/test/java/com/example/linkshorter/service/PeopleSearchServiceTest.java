package com.example.linkshorter.service;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeopleSearchServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PeopleSearchService peopleSearchService;

    @Test
    void initTest() {
        assertNotNull(peopleSearchService);
        assertNotNull(personRepository);
    }

    @Test
    void getPeopleIsNulTest() {
        when(personRepository.getByUsername("user1")).thenReturn(null);

        assertTrue(peopleSearchService.getPeople("user1"));
    }

    @Test
    void getPeopleIsNotNulTest() {
        when(personRepository.getByUsername("user1")).thenReturn(new Person());

        assertFalse(peopleSearchService.getPeople("user1"));
    }
}
