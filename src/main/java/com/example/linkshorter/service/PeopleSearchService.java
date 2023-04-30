package com.example.linkshorter.service;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeopleSearchService {

    private final PersonRepository personRepository;

    public boolean getPeople(String login) {
        Person person = personRepository.getByUsername(login);
        return person == null;
    }
}
