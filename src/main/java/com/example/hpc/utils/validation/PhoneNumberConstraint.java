package com.example.hpc.utils.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberConstraint implements ConstraintValidator<PhoneNumber, String> {
	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return Pattern.matches(RegexConstants.PHONE_NUMBER_PATTERN, value);
	}
}
