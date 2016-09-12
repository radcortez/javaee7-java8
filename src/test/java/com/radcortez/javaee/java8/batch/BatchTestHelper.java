package com.radcortez.javaee.java8.batch;

import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
public class BatchTestHelper {
    private static final int MAX_TRIES = 10;
    private static final int THREAD_SLEEP = 1000;

    private BatchTestHelper() {
        throw new UnsupportedOperationException();
    }

    public static JobExecution keepTestAlive(JobExecution jobExecution) throws InterruptedException {
        int maxTries = 0;
        while (!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
            if (maxTries < MAX_TRIES) {
                maxTries++;
                Thread.sleep(THREAD_SLEEP);
                jobExecution = BatchRuntime.getJobOperator().getJobExecution(jobExecution.getExecutionId());
            } else {
                break;
            }
        }
        return jobExecution;
    }
}
