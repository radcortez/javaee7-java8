package com.radcortez.javaee.java8.jpa.annotations;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.LockModeType.NONE;

/**
 * @author Roberto Cortez
 */
@Target({TYPE})
@Retention(RUNTIME)
@Repeatable(NamedQueriesEnhanced.class)
public @interface NamedQueryEnhanced {
    String name();

    String query();

    LockModeType lockMode() default NONE;

    QueryHint[] hints() default {};
}
