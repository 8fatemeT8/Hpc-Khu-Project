package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.JobResultDomain;
import com.example.hpc.model.dto.JobResultDto;
import com.example.hpc.model.entity.JobResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {})
public interface JobResultMapper extends MapperBase<JobResult, JobResultDto, JobResultDomain> {

    @Override
    default JobResult createNew() {
        return new JobResult();
    }
}
