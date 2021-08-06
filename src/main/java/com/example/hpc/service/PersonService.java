package com.example.hpc.service;

import com.example.hpc.model.domain.PersonDomain;
import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.PersonDto;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.PersonRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.PersonCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.PersonMapper;
import com.example.hpc.utils.mapper.UserMapper;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.predicates.PersonPredicate;
import com.example.hpc.utils.validation.Validations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

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

	public PersonService(PersonRepository personRepository, PersonMapper personMapper, PersonPredicate personPredicate,
						 UserService userService, UserMapper userMapper, WalletService walletService, WalletMapper walletMapper) {
		super(personRepository, personMapper, personPredicate);
		this.personRepository = personRepository;
		this.personMapper = personMapper;
		this.userService = userService;
		this.userMapper = userMapper;
		this.walletService = walletService;
		this.walletMapper = walletMapper;
	}

	public Person getPersonByUserUsername(String username) {
		return personRepository.findPersonByUserUsername(username);
	}

	@Override
	@Transactional
	public void add(PersonDto personDto, Person person) {
		person.setUser(userMapper.toEntity(userService.createAndUpdate(personDto.getUser())));
		person.setWallet(walletMapper.toEntity(walletService.createAndUpdate(new WalletDto(0L))));
	}

	@Override
	public void update(PersonDto personDto, Person person) {
		person.setUpdateDate(Instant.now());
	}

	@Override
	public void validate(Person person) {
		// TODO add person validation and check acceess
	}

	public PersonDomain getPersonByWallet(Wallet wallet){
		return personMapper.toDomain(personRepository.findByWallet(wallet));
	}
}
