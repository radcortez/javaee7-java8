package com.radcortez.javaee.java8.validation;

import javax.validation.constraints.NotNull;

/**
 * @author Roberto Cortez
 */
public class Driver {
    @NotNull(message = "name may not be null")
    private String name;
    @NotNull(message = "licence may not be null")
    private String licence;

    public Driver() {
    }

    public Driver(String name, String licence) {
        this.name = name;
        this.licence = licence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }
}
