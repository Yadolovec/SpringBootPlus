package com.RESTApp.App.controllers;

import com.RESTApp.App.models.Person;
import com.RESTApp.App.services.PeopleService;
import com.RESTApp.App.utils.PersonNotCreatedException;
import com.RESTApp.App.utils.PersonErrorHandler;
import com.RESTApp.App.utils.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult){

        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List <FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new PersonNotCreatedException(errorMsg.toString());

        }
        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorHandler> errorHandler(UserNotFoundException e){
        PersonErrorHandler personErrorHandler = new PersonErrorHandler(
                "User with this id not found",
                System.currentTimeMillis());
        return new ResponseEntity<>(personErrorHandler, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorHandler> errorHandler(PersonNotCreatedException e){
        PersonErrorHandler personErrorHandler = new PersonErrorHandler(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(personErrorHandler, HttpStatus.BAD_REQUEST);
    }
}
