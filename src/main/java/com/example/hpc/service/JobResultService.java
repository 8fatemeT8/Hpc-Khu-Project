package com.example.hpc.service;

import com.example.hpc.model.domain.JobResultDomain;
import com.example.hpc.model.dto.JobResultDto;
import com.example.hpc.model.entity.JobResult;
import com.example.hpc.model.repository.JobResultRepository;
import com.example.hpc.utils.mapper.JobResultMapper;
import org.springframework.stereotype.Service;

@Service
public class JobResultService extends ServiceBase<JobResult, JobResultDto, JobResultDomain, JobResultRepository, JobResultMapper> {

    public JobResultService(JobResultRepository jobResultRepository, JobResultMapper jobResultMapper) {
        super(jobResultRepository, jobResultMapper);
    }
}
