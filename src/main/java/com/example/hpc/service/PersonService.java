package com.example.hpc.service;

import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.utils.mapper.PersonMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ServiceBase<Person, PersonDto, PersonDomain, PersonRepository, PersonMapper> {

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        super(personRepository, personMapper);
    }
}
