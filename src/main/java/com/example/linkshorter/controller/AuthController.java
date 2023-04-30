package com.example.linkshorter.controller;

import com.example.linkshorter.model.LinkTO;
import com.example.linkshorter.model.Person;
import com.example.linkshorter.service.RegistrationService;
import com.example.linkshorter.validation.PersonValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthController {

    private final PersonValidation validation;
    private final RegistrationService service;

    @GetMapping("${application.point.head}")
    String auth(Model model) {
        model.addAttribute("link", new LinkTO());
        return "head";
    }

    @GetMapping("${application.point.registration}")
    String registration(@ModelAttribute("person") Person person) {
        return "registration";
    }

    @PostMapping("${application.point.registration}")
    String performRegistration(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        validation.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        service.register(person);
        return "redirect:/login";
    }
}
