package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.model.entity.Person;
import com.example.hpc.utils.filtering.criteria.PersonCriteria;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class PersonPredicate extends PredicateBase<Person, PersonCriteria>{
    public PersonPredicate(EntityManager entityManager) {
        super(entityManager, Person.class);
    }

    @Override
    public List<Predicate> filter(PersonCriteria personCriteria) {
        return predicates;
    }
}
