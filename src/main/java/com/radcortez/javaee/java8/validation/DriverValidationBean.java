package com.radcortez.javaee.java8.validation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

/**
 * @author Roberto Cortez
 */
@Singleton
public class DriverValidationBean {
    @Inject
    private Validator validator;

    public String validateDriver(final Driver driver) {
        return validator.validate(driver)
                 .stream()
                 .map(ConstraintViolation::getMessage)
                 .collect(Collectors.joining("\n"));
    }
}
