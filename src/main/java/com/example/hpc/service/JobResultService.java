package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.JobResultDomain;
import com.example.hpc.model.dto.JobResultDto;
import com.example.hpc.model.entity.JobResult;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.JobResultRepository;
import com.example.hpc.service.bases.ServiceBase;
import com.example.hpc.utils.FileStorageService;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.JobResultMapper;
import org.apache.struts.taglib.html.HtmlTag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class JobResultService extends ServiceBase<JobResult, JobResultDto, JobResultDomain, JobResultRepository, JobResultMapper> {

    private JobResultRepository jobResultRepository;
    private FileStorageService fileStorageService;
    private JwtUserDetailsService jwtUserDetailsService;

    public JobResultService(JobResultRepository jobResultRepository, JobResultMapper jobResultMapper, FileStorageService fileStorageService, JwtUserDetailsService jwtUserDetailsService) {
        super(jobResultRepository, jobResultMapper);
        this.jobResultRepository = jobResultRepository;
        this.fileStorageService = fileStorageService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public Resource getResultFile(String resultName) {
        // TODO : add access for master to download student result job
        return fileStorageService.loadResultFileAsResource(resultName);
    }

    public JobResult getByResultFileName(String resultName) {
        return jobResultRepository.findByResultFileName(resultName);
    }

    @Override
    public JobResultDomain getOne(Long id) throws ExceptionHandler {
        User user = jwtUserDetailsService.getCurrentUser();
        if (Arrays.asList(UserRoles.SYS_ADMIN, UserRoles.ADMIN).contains(user.getRole().getRoleName()))
            throw new ExceptionHandler("you dont have access for get this object", HttpStatus.FORBIDDEN.value());
        return super.getOne(id);
    }
}
