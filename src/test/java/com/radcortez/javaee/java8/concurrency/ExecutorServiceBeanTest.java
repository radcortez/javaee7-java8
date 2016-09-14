package com.radcortez.javaee.java8.concurrency;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class ExecutorServiceBeanTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.concurrency")
                         .addAsWebInfResource("beans.xml");
    }

    @EJB
    private ManagedExecutorServiceBean managedExecutorServiceBean;
    @EJB
    private ManagedScheduledExecutorServiceBean managedScheduledExecutorServiceBean;

    @Test
    public void testExecuteTask() throws Exception {
        managedExecutorServiceBean.executeTask();
    }

    @Test
    public void testExecuteScheduledTask() throws Exception {
        managedScheduledExecutorServiceBean.executeTask();
        SECONDS.sleep(15);
    }
}
