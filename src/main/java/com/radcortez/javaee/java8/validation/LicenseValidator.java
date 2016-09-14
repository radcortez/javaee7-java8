package com.radcortez.javaee.java8.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @author Roberto Cortez
 */
public class LicenseValidator implements ConstraintValidator<License, String> {
    @Override
    public void initialize(final License constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                       .filter(v -> v.startsWith("PRT"))
                       .isPresent();
    }
}
