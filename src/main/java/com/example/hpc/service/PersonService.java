package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.FileStorageService;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.filtering.criteria.PersonCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.mapper.UserMapper;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.filtering.predicates.PersonPredicate;
import com.example.hpc.utils.validation.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;

@Service
public class PersonService extends ServiceWithSearchBase<Person, PersonDto, PersonDomain,
        PersonRepository, PersonMapper, PersonCriteria, PersonPredicate>
        implements BeforeAdd<Person, PersonDto>, BeforeUpdate<Person, PersonDto>, Validations<Person> {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private FileStorageService fileStorageService;
    private JwtUserDetailsService jwtUserDetailsService;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper, PersonPredicate personPredicate,
                         UserService userService, UserMapper userMapper, WalletService walletService,
                         WalletMapper walletMapper, FileStorageService fileStorageService, JwtUserDetailsService jwtUserDetailsService) {
        super(personRepository, personMapper, personPredicate);
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.walletService = walletService;
        this.walletMapper = walletMapper;
        this.fileStorageService = fileStorageService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public Person getPersonByUserUsername(String username) {
        return personRepository.findPersonByUserUsername(username);
    }

    @Override
    @Transactional
    public void add(PersonDto personDto, Person person) {
        person.setCreateDate(Instant.now());
        userService.validate(person.getUser());
        person.setWallet(new Wallet(0L));
        userService.add(personDto.getUser(), person.getUser());
    }

    @Override
    public void update(PersonDto personDto, Person person) {
        userService.update(personDto.getUser(), person.getUser());
        person.setUpdateDate(Instant.now());
    }

    @Override
    public void validate(Person person) {
        // TODO add person validation and check access

        User user = jwtUserDetailsService.getCurrentUser();
        if (person.getId() != null)
            if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
                if (!person.getUser().getId().equals(user.getId()))
                    throw new ExceptionHandler("you cant change other data", HttpStatus.FORBIDDEN.value());
            }
    }

    public void setUserImage(MultipartFile imageFile, User user) {
        Person dbPerson = personRepository.findPersonByUserUsername(user.getUsername());
        String imageFileName = fileStorageService.storeUserImageFile(imageFile, dbPerson);
        dbPerson.setPersonalImage(imageFileName);

        personRepository.save(dbPerson);
    }

    @Override
    public PersonDomain getOne(Long id) throws ExceptionHandler {
        User user = jwtUserDetailsService.getCurrentUser();
        if (Arrays.asList(UserRoles.STUDENT).contains(user.getRole().getRoleName())) {
                throw new ExceptionHandler("you cant get other data", HttpStatus.FORBIDDEN.value());
        }
        return super.getOne(id);
    }
}
