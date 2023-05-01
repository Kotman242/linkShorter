package com.example.linkshorter.validation;

import com.example.linkshorter.model.Person;
import com.example.linkshorter.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class PersonValidation implements Validator {

    private final SearchService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (service.getPeople(person.getUsername())) {
            return;
        }
        errors.rejectValue("username", "",
                "Пользователь с данным именем уже существует");
    }
}
