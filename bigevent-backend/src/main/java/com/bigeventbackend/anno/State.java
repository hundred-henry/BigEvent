package com.bigeventbackend.anno;

import com.bigeventbackend.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StateValidation.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    String message() default "{state parameters can only be posted or draft}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
