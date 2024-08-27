package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.security.PersonDetails;
import ru.kata.spring.boot_security.demo.services.PersonService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getUserInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        Person person = personService.getByName(personDetails.getUsername());
        model.addAttribute("person", person);

        return "person/person_info_user";
    }
}
