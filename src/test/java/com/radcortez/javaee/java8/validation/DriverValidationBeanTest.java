package com.radcortez.javaee.java8.validation;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class DriverValidationBeanTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.validation")
                         .addAsWebInfResource("beans.xml");
    }

    @Inject
    private DriverValidationBean driverValidationBean;

    @Test
    public void testValidateDriver() throws Exception {
        final String validateDriver = driverValidationBean.validateDriver(new Driver("Roberto", "AA-1111"));
        System.out.println("validateDriver = " + validateDriver);

        final String nullDriver = driverValidationBean.validateDriver(new Driver());
        System.out.println("nullDriver = " + nullDriver);
    }
}
