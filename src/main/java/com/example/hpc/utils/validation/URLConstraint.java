package com.example.hpc.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class URLConstraint implements ConstraintValidator<URL, String> {
	@Override
	public void initialize(URL constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return Pattern.matches(RegexConstants.URL_PATTERN, value);
	}
}
