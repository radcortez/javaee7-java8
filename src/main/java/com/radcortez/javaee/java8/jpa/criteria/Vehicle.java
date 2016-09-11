package com.radcortez.javaee.java8.jpa.criteria;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import static javax.persistence.EnumType.STRING;

/**
 * @author Roberto Cortez
 */
@Entity
public class Vehicle {
    @Id
    private Long id;
    private String plate;
    @Enumerated(STRING)
    private Type type;

    public Vehicle() {
    }

    public Vehicle(Long id, String plate, Type type) {
        this.id = id;
        this.plate = plate;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static enum Type {
        CAR, BIKE, TRUCK
    }
}
