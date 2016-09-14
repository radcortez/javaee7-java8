package com.radcortez.javaee.java8.validation;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Roberto Cortez
 */
@Vetoed
public class EnhancedValidatorBean {
    @Inject
    private EnhancedValidator enhancedValidator;

    @PostConstruct
    public void init() {
        enhancedValidator.addConstraintValidator(License.class, String.class, this::licenseValid);

        enhancedValidator.addConstraintValidator(NotNull.class, String.class, String::isEmpty);
    }

    private boolean licenseValid(final String value) {
        return Optional.ofNullable(value)
                         .filter(v -> v.startsWith("PRT"))
                         .isPresent();
    }
}
