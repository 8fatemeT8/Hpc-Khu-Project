package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TicketStateConverter implements AttributeConverter<TicketState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketState applicationType) {
        if (applicationType != null)
            return applicationType.toKey();
        return null;
    }

    @Override
    public TicketState convertToEntityAttribute(Integer integer) {
        if (integer != null)
            return TicketState.fromKey(integer);
        return null;
    }
}
