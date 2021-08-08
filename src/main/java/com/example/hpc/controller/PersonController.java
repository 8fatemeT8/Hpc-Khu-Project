package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.config.jwt.JwtUserDetailsService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController extends ControllerWithSearchBase<Person, PersonDto, PersonDomain,
        PersonRepository, PersonMapper, PersonService, PersonCriteria, PersonPredicate> {

    private PersonService personService;
    private final UserAuthentication userAuthentication;
    private JwtUserDetailsService jwtUserDetailsService;

    public PersonController(PersonService personService, UserAuthentication userAuthentication, JwtUserDetailsService jwtUserDetailsService) {
        super(personService);
        this.personService = personService;
        this.userAuthentication = userAuthentication;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping("/account")
    public ResponseEntity<JwtResponse> createPerson(@Valid @RequestBody PersonDto personDto) {
        super.create(personDto);
        return ResponseEntity.ok(userAuthentication.authenticate(personDto.getUser().getUsername(), personDto.getUser().getPassword()));
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImageFile(@RequestPart() MultipartFile imageFile) {
        personService.setUserImage(imageFile, jwtUserDetailsService.getCurrentUser());
        return ResponseEntity.ok().build();
    }
}
