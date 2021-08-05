package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.PersonService;
import com.example.hpc.utils.criteria.PersonCriteria;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.predicates.PersonPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController extends ControllerWithSearchBase<Person, PersonDto, PersonDomain,
		PersonRepository, PersonMapper, PersonService, PersonCriteria, PersonPredicate> {

	public PersonController(PersonService personService) {
		super(personService);
	}
}
