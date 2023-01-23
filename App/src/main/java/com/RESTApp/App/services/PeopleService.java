package com.RESTApp.App.services;



import com.RESTApp.App.models.Person;
import com.RESTApp.App.repo.PeopleRepository;
import com.RESTApp.App.utils.PersonErrorHandler;
import com.RESTApp.App.utils.UserNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
//    private final EntityManager entityManager;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
//        this.entityManager = entityManager;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(Person person){
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setWhoCreated("ADMIN");
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }


}
