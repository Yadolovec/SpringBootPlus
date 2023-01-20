package com.BootTraining.SecurityApp.utils;

import com.BootTraining.SecurityApp.models.Person;
import com.BootTraining.SecurityApp.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.findByUsername(person.getUsername())==null)
            return;

        errors.rejectValue("username", "", "This username is taken");
    }
}
