package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.config.jwt.SecurityUtils;
import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.FileStorageService;
import com.example.hpc.utils.filtering.criteria.JobCriteria;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.JobMapper;
import com.example.hpc.utils.filtering.predicates.JobPredicate;
import com.example.hpc.utils.validation.Validations;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;

@Service
public class JobService extends ServiceWithSearchBase<Job, JobDto, JobDomain,
        JobRepository, JobMapper, JobCriteria, JobPredicate> implements BeforeAdd<Job, JobDto>, Validations<Job>, BeforeUpdate<Job, JobDto> {

    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private final FileStorageService fileStorageService;
    private final PersonService personService;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JobService(JobRepository jobRepository, JobMapper jobMapper,
                      JobPredicate jobPredicate, FileStorageService fileStorageService,
                      PersonService personService, JwtUserDetailsService jwtUserDetailsService) {
        super(jobRepository, jobMapper, jobPredicate);
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
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
        job.setCreateDate(Instant.now());
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
        // TODO add checking user verify
        User user = jwtUserDetailsService.getCurrentUser();

        if (Arrays.asList(UserRoles.ADMIN.getName(), UserRoles.SYS_ADMIN.getName())
                .contains(user.getRole().getRoleName().getName())) {
            throw new ExceptionHandler("you cant do this action", HttpStatus.FORBIDDEN.value());
        }

        if (job.getPerson() != null)
            if (!user.getId().equals(job.getPerson().getUser().getId()))
                throw new ExceptionHandler("you cant change this object", HttpStatus.NOT_ACCEPTABLE.value());
    }

    /**
     * set update date , replace jobFile
     *
     * @param jobDto
     * @param job
     */
    @Override
    public void update(JobDto jobDto, Job job) {
        job.setUpdateDate(Instant.now());

        if (jobDto.getJobFile() != null)
            if (job.getJobResults().isEmpty())
                fileStorageService.replaceFile(jobDto.getJobFile(), job.getJobFile());

        if (job.getPerson() == null) {
            job.setPerson(personService.getPersonByUserUsername(SecurityUtils.getCurrentUserLogin().get()));
        }
    }

    /**
     * return job file for the job owner
     *
     * @param fileName
     * @return
     */
    public Resource getJobFile(String fileName) {
        // TODO : add access for master to get just student job or own job
        return fileStorageService.loadFileAsResource(fileName);
    }

    /**
     * find job by job file name
     *
     * @param fileName
     * @return
     */
    public Job getJobByJobFileName(String fileName) {
        return jobRepository.findByJobFile(fileName);
    }

    @Override
    public JobDomain getOne(Long id) throws ExceptionHandler {
        User user = jwtUserDetailsService.getCurrentUser();
        if (user.getRole().getRoleName().equals(UserRoles.STUDENT)) {
            Person person = personService.getPersonByUserUsername(user.getUsername());
            return jobMapper.toDomain(jobRepository.findByIdAndPerson(id, person));
        }
        // TODO : add access for master to get just student job or own job
        return super.getOne(id);
    }
}
