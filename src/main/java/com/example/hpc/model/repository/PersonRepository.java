package com.example.hpc.model.repository;

import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.Wallet;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends RepositoryBase<Person> {
	Person findPersonByUserUsername(String username);
	Person findByWallet(Wallet wallet);
}
