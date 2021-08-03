package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class JobPlanConverter implements AttributeConverter<JobPlan, Integer> {
    @Override
    public Integer convertToDatabaseColumn(JobPlan jobPlan) {
        return jobPlan.toKey();
    }

    @Override
    public JobPlan convertToEntityAttribute(Integer integer) {
        return JobPlan.fromKey(integer);
    }
}
