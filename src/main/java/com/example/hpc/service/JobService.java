package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.config.jwt.SecurityUtils;
import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.FileStorageService;
import com.example.hpc.utils.criteria.JobCriteria;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.JobMapper;
import com.example.hpc.utils.predicates.JobPredicate;
import com.example.hpc.utils.validation.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;

@Service
public class JobService extends ServiceWithSearchBase<Job, JobDto, JobDomain,
		JobRepository, JobMapper, JobCriteria, JobPredicate> implements BeforeAdd<Job, JobDto>, Validations<Job>, BeforeUpdate<Job, JobDto> {

	private final FileStorageService fileStorageService;
	private final PersonService personService;
	private final JwtUserDetailsService jwtUserDetailsService;

	public JobService(JobRepository jobRepository, JobMapper jobMapper,
					  JobPredicate jobPredicate, FileStorageService fileStorageService,
					  PersonService personService, JwtUserDetailsService jwtUserDetailsService) {
		super(jobRepository, jobMapper, jobPredicate);
		this.fileStorageService = fileStorageService;
		this.personService = personService;
		this.jwtUserDetailsService = jwtUserDetailsService;
	}

	/**
	 * this method stores job file and sets name of file in job entity
	 *
	 * @param jobDto
	 * @param job
	 */
	@Override
	@Transactional
	public void add(JobDto jobDto, Job job) {
		String jobFileName = fileStorageService.storeFile(jobDto.getJobFile());
		job.setJobFile(jobFileName);

		job.setPerson(personService.getPersonByUserUsername(SecurityUtils.getCurrentUserLogin().get()));
	}

	/**
	 * this method validate job
	 * only admin, sysAdmin and jobOwner can change job detail
	 *
	 * @param job
	 */
	@Override
	public void validate(Job job) {
		User user = jwtUserDetailsService.getCurrentUser();

		if (Arrays.asList(UserRoles.ADMIN.getName(), UserRoles.SYS_ADMIN.getName())
				.contains(user.getRole().getRoleName())) {
			return;
		}

		if (job.getPerson() != null)
			if (!user.getId().equals(job.getPerson().getUser().getId()))
				throw new ExceptionHandler("you cant change this object", HttpStatus.NOT_ACCEPTABLE.value());
	}

	/**
	 * set update date
	 *
	 * @param jobDto
	 * @param job
	 */
	@Override
	public void update(JobDto jobDto, Job job) {
		job.setUpdateDate(Instant.now());
	}
}
