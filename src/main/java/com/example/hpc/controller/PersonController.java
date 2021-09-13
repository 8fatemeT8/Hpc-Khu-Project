package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtResponse;
import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.config.jwt.UserAuthentication;
import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.PersonService;
import com.example.hpc.service.ReCaptchaVerifierService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.filtering.criteria.PersonCriteria;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.filtering.predicates.PersonPredicate;
import org.hibernate.mapping.Array;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/person")
public class PersonController extends ControllerWithSearchBase<Person, PersonDto, PersonDomain,
        PersonRepository, PersonMapper, PersonService, PersonCriteria, PersonPredicate> {

    private PersonService personService;
    private final UserAuthentication userAuthentication;
    private JwtUserDetailsService jwtUserDetailsService;
    private ReCaptchaVerifierService reCaptchaVerifierService;

    public PersonController(PersonService personService, UserAuthentication userAuthentication,
                            JwtUserDetailsService jwtUserDetailsService, ReCaptchaVerifierService reCaptchaVerifierService) {
        super(personService);
        this.personService = personService;
        this.userAuthentication = userAuthentication;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.reCaptchaVerifierService = reCaptchaVerifierService;
    }

    @PostMapping("/account")
    public ResponseEntity<JwtResponse> createPerson(@Valid @RequestBody PersonDto personDto, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse) {
        boolean verified = reCaptchaVerifierService.verify(recaptchaResponse);
        if (!verified) {
            throw new ExceptionHandler("RECAPTCHA_VERIFICATION_ERROR", HttpStatus.NOT_ACCEPTABLE.value());
        }
        super.create(personDto);
        return ResponseEntity.ok(userAuthentication.authenticate(personDto.getUser().getUsername(), personDto.getUser().getPassword()));
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImageFile(@RequestPart() MultipartFile imageFile) {
        personService.setUserImage(imageFile, jwtUserDetailsService.getCurrentUser());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PagedResult<PersonDomain>> getAll(PersonCriteria personCriteria, Pageable pageable) {
        User user = jwtUserDetailsService.getCurrentUser();

        if (user.getRole().getRoleName().equals(UserRoles.STUDENT))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return super.getAll(personCriteria, pageable);
    }

    @Override
    public ResponseEntity<PersonDomain> create(@Valid PersonDto personDto) {
        User user = jwtUserDetailsService.getCurrentUser();

        if (Arrays.asList(UserRoles.SYS_ADMIN, UserRoles.ADMIN).contains(user.getRole().getRoleName()))
            return super.create(personDto);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // disable delete api
    @Override
    public ResponseEntity<?> deleteById(Long id) {
//        return super.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
