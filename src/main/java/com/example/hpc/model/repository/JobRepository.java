package com.example.hpc.model.repository;

import com.example.hpc.model.entity.Job;
import com.example.hpc.model.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends RepositoryBase<Job> {
    Job findByJobFile(String jobFile);
    Job findByIdAndPerson(Long id , Person person);
}
