package com.radcortez.javaee.java8.timer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class TimerBeanTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.timer")
                         .addAsWebInfResource("beans.xml");
    }

    @Test
    public void testTimeout() throws Exception {
        MINUTES.sleep(1);
    }
}
