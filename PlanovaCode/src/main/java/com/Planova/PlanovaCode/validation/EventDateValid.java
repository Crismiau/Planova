package com.Planova.PlanovaCode.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventDateValidator.class)
public @interface EventDateValid {
    String message() default "La fecha de inicio debe ser menor que la fecha fin.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}