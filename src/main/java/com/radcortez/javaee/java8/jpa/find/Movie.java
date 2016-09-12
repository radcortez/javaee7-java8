package com.radcortez.javaee.java8.jpa.find;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Roberto Cortez
 */
@Entity
public class Movie {
    @Id
    private Long id;
    private String name;

    public Movie() {
    }

    public Movie(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
