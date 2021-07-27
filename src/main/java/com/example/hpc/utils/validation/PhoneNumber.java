package com.example.hpc.utils.validation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

	String message() default "Invalid phone number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}