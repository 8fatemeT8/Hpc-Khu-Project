package com.example.hpc.utils.predicates;

import com.example.hpc.model.entity.Person;
import com.example.hpc.utils.criteria.PersonCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonPredicate extends PredicateBase<Person, PersonCriteria>{
    public PersonPredicate(EntityManager entityManager) {
        super(entityManager, Person.class);
    }

    @Override
    public List<Person> filter(PersonCriteria personCriteria, Pageable pageable) {
        return null;
    }
}
