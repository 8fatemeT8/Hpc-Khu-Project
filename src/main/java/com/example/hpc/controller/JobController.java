package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.JobRepository;
import com.example.hpc.service.JobService;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.filtering.criteria.JobCriteria;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.JobMapper;
import com.example.hpc.utils.filtering.predicates.JobPredicate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * admin and user can user this controller
 */
@RestController
@RequestMapping("/api/job")
public class JobController extends ControllerWithSearchBase<Job, JobDto,
        JobDomain, JobRepository, JobMapper, JobService, JobCriteria, JobPredicate> {

    private JobService jobService;
    private JwtUserDetailsService jwtUserDetailsService;

    public JobController(JobService jobService, JwtUserDetailsService jwtUserDetailsService) {
        super(jobService);
        this.jobService = jobService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    /**
     * override this api for get data in formDate (ModelAttribute)
     * get data in form data
     * httpMethod : Post
     *
     * @param jobDto
     * @return ResponseEntity<JobDomain>
     */
    @Override
    public ResponseEntity<JobDomain> create(@Valid @ModelAttribute JobDto jobDto) {
        return super.create(jobDto);
    }

    @Override
    public ResponseEntity<JobDomain> update(@Valid @ModelAttribute JobDto jobDto) {
        return super.update(jobDto);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(jwtUserDetailsService.getCurrentUser().getRole().getRoleName()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return super.deleteById(id);
    }

    /**
     * return job file for job owner
     * get job file name in PathVariable
     * httpMethod : Get
     *
     * @param fileName
     * @return ResponseEntity<Resource>
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadJobFile(@PathVariable("fileName") String fileName) {
        User user = jwtUserDetailsService.getCurrentUser();
        if (user.getRole().getRoleName().equals(UserRoles.STUDENT)) {
            if (!jobService.getJobByJobFileName(fileName).getPerson().getUser().getId().equals(user.getId()))
                throw new ExceptionHandler("you have not access to download this file", HttpStatus.FORBIDDEN.value());
        }
        return ResponseEntity.ok().body(jobService.getJobFile(fileName));
    }
}
