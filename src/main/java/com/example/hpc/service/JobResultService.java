package com.example.hpc.service;

import com.example.hpc.model.domain.JobResultDomain;
import com.example.hpc.model.dto.JobResultDto;
import com.example.hpc.model.entity.JobResult;
import com.example.hpc.model.repository.JobResultRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.FileStorageService;
import com.example.hpc.utils.mapper.JobResultMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class JobResultService extends ServiceBase<JobResult, JobResultDto, JobResultDomain, JobResultRepository, JobResultMapper> {

    private JobResultRepository jobResultRepository;
    private FileStorageService fileStorageService;

    public JobResultService(JobResultRepository jobResultRepository, JobResultMapper jobResultMapper, FileStorageService fileStorageService) {
        super(jobResultRepository, jobResultMapper);
        this.jobResultRepository = jobResultRepository;
        this.fileStorageService = fileStorageService;
    }

    public Resource getResultFile(String resultName){
        return fileStorageService.loadResultFileAsResource(resultName);
    }

    public JobResult getByResultFileName(String resultName){
        return jobResultRepository.findByResultFileName(resultName);
    }
}
