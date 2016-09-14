package com.radcortez.javaee.java8.jpa.criteria;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.radcortez.javaee.java8.jpa.criteria.Vehicle.Type.CAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class CriteriaTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.jpa.criteria")
                         .addAsWebInfResource("beans.xml")
                         .addAsResource("META-INF/persistence.xml")
                         .addAsResource("META-INF/load-vehicles.sql", "META-INF/load.sql");
    }

    @Inject
    private VehicleBean vehicleBean;

    @Test
    public void testFindVehicles() throws Exception {
        final List<Vehicle> vehiclesAll = vehicleBean.findVehicles(null);
        assertNotNull(vehiclesAll);
        assertEquals(3, vehiclesAll.size());

        final List<Vehicle> vehiclesByType = vehicleBean.findVehicles(CAR);
        assertNotNull(vehiclesByType);
        assertEquals(1, vehiclesByType.size());
        assertEquals(CAR, vehiclesByType.get(0).getType());
    }

    @Test
    public void testFindVehiclesCriteria() throws Exception {
        final List<Vehicle> vehiclesAll = vehicleBean.findVehiclesCriteria(null);
        assertNotNull(vehiclesAll);
        assertEquals(3, vehiclesAll.size());

        final List<Vehicle> vehiclesByType = vehicleBean.findVehiclesCriteria(CAR);
        assertNotNull(vehiclesByType);
        assertEquals(1, vehiclesByType.size());
        assertEquals(CAR, vehiclesByType.get(0).getType());
    }

    @Test
    public void testFindVehiclesEnhanced() throws Exception {
        final List<Vehicle> vehiclesAll = vehicleBean.findVehiclesEnhanced(Optional.empty());
        assertNotNull(vehiclesAll);
        assertEquals(3, vehiclesAll.size());

        final List<Vehicle> vehiclesByType = vehicleBean.findVehiclesEnhanced(Optional.of(CAR));
        assertNotNull(vehiclesByType);
        assertEquals(1, vehiclesByType.size());
        assertEquals(CAR, vehiclesByType.get(0).getType());
    }
}
