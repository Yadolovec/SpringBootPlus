package com.RESTApp.App.controllers;

import com.RESTApp.App.DTO.PersonDTO;
import com.RESTApp.App.models.Person;
import com.RESTApp.App.services.PeopleService;
import com.RESTApp.App.utils.PersonNotCreatedException;
import com.RESTApp.App.utils.PersonErrorHandler;
import com.RESTApp.App.utils.UserNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper mapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper mapper) {
        this.peopleService = peopleService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<PersonDTO> getAllPeople(){
        return peopleService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id){

        return convertToDTO(peopleService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){

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
        peopleService.save(convertFromDTO(personDTO));
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

    Person convertFromDTO(PersonDTO personDTO){
//        Person person = new Person();
//        person.setName(personDTO.getName());
//        person.setAge(personDTO.getAge());
//        person.setEmail(personDTO.getEmail());
//        return person;

//        MADE A @Bean
//        ModelMapper mapper = new ModelMapper();

        return mapper.map(personDTO, Person.class);
    }

    PersonDTO convertToDTO(Person person){
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setName(person.getName());
//        personDTO.setAge(person.getAge());
//        personDTO.setEmail(person.getEmail());
//        return personDTO;
        return mapper.map(person, PersonDTO.class);
    }

}
