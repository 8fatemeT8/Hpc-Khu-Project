package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class ApplicationTypeConverter  implements AttributeConverter<ApplicationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ApplicationType applicationType) {
        return applicationType.toKey();
    }

    @Override
    public ApplicationType convertToEntityAttribute(Integer integer) {
        return ApplicationType.fromKey(integer);
    }
}
