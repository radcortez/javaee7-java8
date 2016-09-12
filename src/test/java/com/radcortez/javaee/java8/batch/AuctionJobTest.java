package com.radcortez.javaee.java8.batch;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import java.util.Properties;

import static com.radcortez.javaee.java8.batch.BatchTestHelper.keepTestAlive;
import static javax.batch.runtime.BatchStatus.COMPLETED;
import static org.junit.Assert.assertEquals;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class AuctionJobTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.batch")
                         .addAsWebInfResource("beans.xml")
                         .addAsResource("META-INF/persistence.xml")
                         .addAsResource("META-INF/batch-jobs/auction-job.xml")
                         .addAsResource("META-INF/batch-jobs/auctions.csv");
    }

    @Test
    public void testBatchletProcess() throws Exception {
        final JobOperator jobOperator = BatchRuntime.getJobOperator();
        final Long executionId = jobOperator.start("auction-job", new Properties());
        final JobExecution jobExecution = jobOperator.getJobExecution(executionId);

        keepTestAlive(jobExecution);

        assertEquals(COMPLETED, jobExecution.getBatchStatus());
    }
}