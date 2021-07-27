package com.example.hpc.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = URLConstraint.class)
@Target(value = {METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(value = RUNTIME)
@Pattern(regexp = RegexConstants.URL_PATTERN)
public @interface URL {

	String message() default "URL not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
