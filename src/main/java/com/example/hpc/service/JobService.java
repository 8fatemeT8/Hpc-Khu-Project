package com.example.hpc.service;

import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.JobCriteria;
import com.example.hpc.utils.mapper.JobMapper;
import com.example.hpc.utils.predicates.JobPredicate;
import org.springframework.stereotype.Service;

@Service
public class JobService extends ServiceWithSearchBase<Job, JobDto, JobDomain,
		JobRepository, JobMapper, JobCriteria, JobPredicate> {

	public JobService(JobRepository jobRepository, JobMapper jobMapper, JobPredicate jobPredicate) {
		super(jobRepository, jobMapper, jobPredicate);
	}
}
