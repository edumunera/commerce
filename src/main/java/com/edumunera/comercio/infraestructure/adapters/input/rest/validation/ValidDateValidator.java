package com.edumunera.comercio.infraestructure.adapters.input.rest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

    private Pattern pattern;

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(?::\\d{2})?$");
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        return date != null && isValid(date);
    }

    private boolean isValid(String date) {
        return pattern.matcher(date).matches();
    }
}
