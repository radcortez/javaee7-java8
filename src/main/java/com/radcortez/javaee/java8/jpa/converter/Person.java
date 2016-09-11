package com.radcortez.javaee.java8.jpa.converter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Roberto Cortez
 */
@Entity
public class Person {
    @Id
    private Long id;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime createDate;

    public Person() {
    }

    public Person(Long id, String name, LocalDate birthDate, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.createDate = createDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
