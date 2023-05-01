package com.example.linkshorter.service;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PeopleRegistrationService implements RegistrationService {

    private final PersonRepository repository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void register(Person person) {
        person.setPassword(encoder.encode(person.getPassword()));

        repository.save(person);
    }


}
