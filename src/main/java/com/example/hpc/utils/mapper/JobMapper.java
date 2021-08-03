package com.example.hpc.utils.mapper;

import com.example.hpc.model.domain.JobDomain;
import com.example.hpc.model.dto.JobDto;
import com.example.hpc.model.entity.Job;
import com.example.hpc.utils.enums.ApplicationTypeConverter;
import com.example.hpc.utils.enums.JobPlanConverter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {ApplicationTypeConverter.class, JobPlanConverter.class, JobResultMapper.class})
public interface JobMapper extends MapperBase<Job, JobDto, JobDomain> {
    @Override
    default Job createNew() {
        return new Job();
    }
}
