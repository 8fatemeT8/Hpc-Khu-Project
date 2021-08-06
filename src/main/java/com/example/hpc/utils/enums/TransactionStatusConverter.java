package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, Integer> {
	@Override
	public Integer convertToDatabaseColumn(TransactionStatus transactionStatus) {
		if (transactionStatus != null)
			return transactionStatus.toKey();
		return null;
	}

	@Override
	public TransactionStatus convertToEntityAttribute(Integer integer) {
		if (integer != null)
			return TransactionStatus.fromKey(integer);
		return null;
	}
}
