package com.example.hpc.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordConstraint.class})
public @interface Password {

    String message() default "Password not enough secure ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

