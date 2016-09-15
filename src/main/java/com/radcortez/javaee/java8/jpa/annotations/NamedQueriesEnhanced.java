package com.radcortez.javaee.java8.jpa.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Roberto Cortez
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface NamedQueriesEnhanced {
    NamedQueryEnhanced[] value();
}
