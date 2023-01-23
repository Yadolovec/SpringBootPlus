package com.RESTApp.App.controllers;

import com.RESTApp.App.models.Person;
import com.RESTApp.App.services.PeopleService;
import com.RESTApp.App.utils.PersonErrorHandler;
import com.RESTApp.App.utils.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getAllPeople(){
        return peopleService.findAll();
    }


    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){
        return peopleService.findOne(id);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorHandler> errorHandler(UserNotFoundException e){
        PersonErrorHandler personErrorHandler = new PersonErrorHandler(
                "User with this id not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(personErrorHandler, HttpStatus.NOT_FOUND);
    }
}
