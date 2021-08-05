package com.example.hpc.service;

import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.PersonCriteria;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.predicates.PersonPredicate;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ServiceWithSearchBase<Person, PersonDto, PersonDomain,
		PersonRepository, PersonMapper, PersonCriteria, PersonPredicate> {

	public PersonService(PersonRepository personRepository, PersonMapper personMapper, PersonPredicate personPredicate) {
		super(personRepository, personMapper, personPredicate);
	}
}
