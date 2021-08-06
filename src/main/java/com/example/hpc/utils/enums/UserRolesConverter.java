package com.example.hpc.utils.enums;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class UserRolesConverter implements AttributeConverter<UserRoles, Integer> {
	@Override
	public Integer convertToDatabaseColumn(UserRoles attribute) {
		if (attribute != null)
			return attribute.toKey();
		return null;
	}

	@Override
	public UserRoles convertToEntityAttribute(Integer dbData) {
		if (dbData != null)
			return UserRoles.fromKey(dbData);
		return null;
	}
}
