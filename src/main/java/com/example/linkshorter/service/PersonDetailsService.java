package com.example.linkshorter.service;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.repository.PersonRepository;
import com.example.linkshorter.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = repository.getByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return new PersonDetails(person);
    }
}
