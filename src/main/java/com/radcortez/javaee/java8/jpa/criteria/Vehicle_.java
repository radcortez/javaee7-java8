package com.radcortez.javaee.java8.jpa.criteria;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author Roberto Cortez
 */
@StaticMetamodel(Vehicle.class)
public class Vehicle_ {
    public static volatile SingularAttribute<Vehicle, Long> id;
    public static volatile SingularAttribute<Vehicle, String> plate;
    public static volatile SingularAttribute<Vehicle, Vehicle.Type> type;
}
