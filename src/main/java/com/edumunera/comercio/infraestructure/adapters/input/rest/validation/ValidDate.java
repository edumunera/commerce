package com.edumunera.comercio.infraestructure.adapters.input.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidDateValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {

    String message() default "La fecha no tiene el formato correcto o no está informada";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
