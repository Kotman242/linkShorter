package com.example.linkshorter.repository;

import com.example.linkshorter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person getByUsername(String login);
}
