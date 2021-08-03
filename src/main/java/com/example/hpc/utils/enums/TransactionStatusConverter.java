package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransactionStatus transactionStatus) {
        return transactionStatus.toKey();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(Integer integer) {
        return TransactionStatus.fromKey(integer);
    }
}
