package com.BootTraining.SecurityApp.services;

import com.BootTraining.SecurityApp.models.Person;
import com.BootTraining.SecurityApp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findByUsername(String username){
        Optional<Person> person = peopleRepository.findByUsername(username);

        return person.orElse(null);
    }

    @Transactional
    public void register(Person person){
        peopleRepository.save(person);
    }

}
