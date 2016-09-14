package com.radcortez.javaee.java8.extra;

import java.util.stream.Stream;

/**
 * @author Roberto Cortez
 */
public enum LookupEnum {
    DEFAULT("default"),
    VALUE_A("a"),
    VALUE_B("b");

    private String description;

    LookupEnum(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static LookupEnum of(final String description) {
        return Stream.of(LookupEnum.values())
                     .filter(e -> e.getDescription().equals(description))
                     .findFirst()
                     .orElse(DEFAULT);
    }
}
