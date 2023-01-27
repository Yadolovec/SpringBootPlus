package com.some.app.EmptyApp.controllers;

import com.some.app.EmptyApp.dto.PersonDTO;
import com.some.app.EmptyApp.security.JWTUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.some.app.EmptyApp.models.Person;
import com.some.app.EmptyApp.services.RegistrationService;
import com.some.app.EmptyApp.util.PersonValidator;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author Neil Alishev
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                   BindingResult bindingResult) {

        Person person = convertFromDTO(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("message", "ERRor");

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt_token", token);
    }

    public Person convertFromDTO(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
}
