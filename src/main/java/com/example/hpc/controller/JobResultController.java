package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.controller.bases.ControllerBase;
import com.example.hpc.model.domain.JobResultDomain;
import com.example.hpc.model.dto.JobResultDto;
import com.example.hpc.model.entity.JobResult;
import com.example.hpc.model.repository.JobResultRepository;
import com.example.hpc.service.JobResultService;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.JobResultMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/job-result")
public class JobResultController extends ControllerBase<JobResult, JobResultDto, JobResultDomain, JobResultRepository, JobResultMapper, JobResultService> {

    private JobResultService jobResultService;
    private JwtUserDetailsService jwtUserDetailsService;

    public JobResultController(JobResultService jobResultService, JwtUserDetailsService jwtUserDetailsService) {
        super(jobResultService);
        this.jobResultService = jobResultService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    /**
     * disable create
     *
     * @param jobResultDto
     * @return
     */
    @Override
    public ResponseEntity<JobResultDomain> create(@Valid JobResultDto jobResultDto) {
        return ResponseEntity.ok().build();
    }

    /**
     * disable update
     *
     * @param jobResultDto
     * @return
     */
    @Override
    public ResponseEntity<JobResultDomain> update(@Valid JobResultDto jobResultDto) {
        return ResponseEntity.ok().build();
    }

    /**
     * disable delete
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download/{resultFileName}")
    public ResponseEntity<Resource> downloadResultFile(@PathVariable("resultFileName") String resultName) {
        if (!jobResultService.getByResultFileName(resultName).getJob().getPerson().getUser().getId().equals(jwtUserDetailsService.getCurrentUser().getId()))
            throw new ExceptionHandler("you have not access to download this file", HttpStatus.NOT_ACCEPTABLE.value());
        return ResponseEntity.ok().body(jobResultService.getResultFile(resultName));
    }
}
