package com.example.hpc.utils.predicates;

import com.example.hpc.model.entity.Job;
import com.example.hpc.model.entity.Job_;
import com.example.hpc.model.entity.Person;
import com.example.hpc.utils.criteria.JobCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class JobPredicate extends PredicateBase<Job, JobCriteria> {

    private PersonPredicate personPredicate;

    public JobPredicate(EntityManager entityManager, PersonPredicate personPredicate) {
        super(entityManager, Job.class);
        this.personPredicate = personPredicate;
    }

    @Override
    public List<Job> filter(JobCriteria jobCriteria) {
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
            List<Person> persons = personPredicate.filter(jobCriteria.getPerson());
            predicates.add(getValueIn(Job_.PERSON, persons));
        }


        return null;
    }
}
