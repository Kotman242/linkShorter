package com.example.linkshorter.service;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonDetailsServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonDetailsService personDetailsService;

    @Test
    void initTest() {
        assertNotNull(repository);
        assertNotNull(personDetailsService);
    }

    @Test
    void loadUserByUsernameIsNulTest() {
        when(repository.getByUsername("user1")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> personDetailsService.loadUserByUsername("user1"));
    }

    @Test
    void loadUserByUsernameIsNotNulTest() {
        when(repository.getByUsername("user1")).thenReturn(Person.builder()
                .username("User")
                .password("123")
                .build());

        UserDetails result = personDetailsService.loadUserByUsername("user1");

        assertEquals("User", result.getUsername());
        assertEquals("123", result.getPassword());
    }
}
