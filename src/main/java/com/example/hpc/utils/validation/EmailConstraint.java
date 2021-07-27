package com.example.hpc.utils.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailConstraint implements ConstraintValidator<Email, String> {
	@Override
	public void initialize(Email constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return Pattern.matches(RegexConstants.EMAIL_PATTERN, value);
	}
}