package com.example.hpc.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordConstraint implements ConstraintValidator<Password,String > {
	@Override
	public void initialize(Password password) {

	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if(s == null)
			return true;
		return Pattern.matches(RegexConstants.PASSWORD_PATTERN,s);
	}
}
