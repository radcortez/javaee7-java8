package com.radcortez.javaee.java8.validation;

import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.util.function.Predicate;

/**
 * @author Roberto Cortez
 */
public interface EnhancedValidator extends Validator {
    <T> void addConstraintValidator(final Class<? extends Annotation> constraint,
                                    final Class<T> klass,
                                    final Predicate<T> predicate);
}
