package com.example.hpc.utils.filtering.predicates;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.config.jwt.SecurityUtils;
import com.example.hpc.model.entity.*;
import com.example.hpc.service.PersonService;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.filtering.criteria.JobCriteria;
import com.example.hpc.utils.filtering.criteria.PersonCriteria;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RoleStatus;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

@Component
public class JobPredicate extends PredicateBase<Job, JobCriteria> {

    private PersonPredicate personPredicate;
    private JwtUserDetailsService jwtUserDetailsService;
    private PersonService personService;

    public JobPredicate(EntityManager entityManager, PersonPredicate personPredicate, JwtUserDetailsService jwtUserDetailsService, PersonService personService) {
        super(entityManager, Job.class);
        this.personPredicate = personPredicate;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.personService = personService;
    }

    /*
      TODO : if masters want to see their student job,
       we must add the new table which has relation between master and students
     */

    @Override
    public List<Predicate> filter(JobCriteria jobCriteria) {
        User user = jwtUserDetailsService.getCurrentUser();
        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
            Person person = personService.getPersonByUserUsername(user.getUsername());
            predicates.add(cb.equal(root.get(Job_.PERSON), person));
        }
        if (jobCriteria.getName() != null) {
            addStringPredicate(jobCriteria.getName(), Job_.NAME);
        }
        if (jobCriteria.getAdvanceMode() != null) {
            addBooleanPredicate(jobCriteria.getAdvanceMode(), Job_.ADVANCE_MODE);
        }
        if (jobCriteria.getApplication() != null) {
            addIntegerPredicate(jobCriteria.getApplication(), Job_.APPLICATION);
        }
        if (jobCriteria.getCoreNumber() != null) {
            addIntegerPredicate(jobCriteria.getCoreNumber(), Job_.CORE_NUMBER);
        }
        if (jobCriteria.getDescription() != null) {
            addStringPredicate(jobCriteria.getDescription(), Job_.DESCRIPTION);
        }
        if (jobCriteria.getNodeNumber() != null) {
            addIntegerPredicate(jobCriteria.getNodeNumber(), Job_.NODE_NUMBER);
        }
        if (jobCriteria.getPerson() != null) {
            if (!Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
                List<Person> persons = personPredicate.getResult(jobCriteria.getPerson());
                predicates.add(getValueIn(Job_.PERSON, persons));
            }
        }
        if (jobCriteria.getPlan() != null) {
            addIntegerPredicate(jobCriteria.getPlan(), Job_.PLAN);
        }
        if (jobCriteria.getRamInGB() != null) {
            addIntegerPredicate(jobCriteria.getRamInGB(), Job_.RAM_IN_GB);
        }

        return predicates;
    }
}
