package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.config.jwt.UserAuthentication;
import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.PersonService;
import com.example.hpc.utils.criteria.PersonCriteria;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.predicates.PersonPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController extends ControllerWithSearchBase<Person, PersonDto, PersonDomain,
		PersonRepository, PersonMapper, PersonService, PersonCriteria, PersonPredicate> {

	private final UserAuthentication userAuthentication;

	public PersonController(PersonService personService, UserAuthentication userAuthentication) {
		super(personService);
		this.userAuthentication = userAuthentication;
	}

	@PostMapping("/account")
	public ResponseEntity<JwtResponse> createPerson(@Valid @RequestBody PersonDto personDto) {
		super.create(personDto);
		return ResponseEntity.ok(userAuthentication.authenticate(personDto.getUser().getUsername(), personDto.getUser().getPassword()));
	}
}
