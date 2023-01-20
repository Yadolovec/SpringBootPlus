package com.BootTraining.SecurityApp.controllers;

import com.BootTraining.SecurityApp.models.Person;
import com.BootTraining.SecurityApp.services.PeopleService;
import com.BootTraining.SecurityApp.utils.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    public AuthController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("person", new Person());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrationDone(@ModelAttribute("person") @Valid Person person,
                                   BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";

        peopleService.register(person);

        return "redirect:/auth/login";
    }
}
