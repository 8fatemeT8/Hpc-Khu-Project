package com.example.hpc.service;

import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.utils.mapper.JobMapper;
import org.springframework.stereotype.Service;

@Service
public class JobService extends ServiceBase<Job, JobDto, JobDomain, JobRepository, JobMapper> {

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        super(jobRepository, jobMapper);
    }
}
