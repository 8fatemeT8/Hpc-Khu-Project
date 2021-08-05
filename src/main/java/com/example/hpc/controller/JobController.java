package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.service.JobService;
import com.example.hpc.utils.criteria.JobCriteria;
import com.example.hpc.utils.mapper.JobMapper;
import com.example.hpc.utils.predicates.JobPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController extends ControllerWithSearchBase<Job, JobDto,
		JobDomain, JobRepository, JobMapper, JobService, JobCriteria, JobPredicate> {

	public JobController(JobService jobService) {
		super(jobService);
	}
}
