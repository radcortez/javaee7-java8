package com.radcortez.javaee.java8.batch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Roberto Cortez
 */
public class BatchEnhancedFuture {
    @PersistenceContext
    private EntityManager entityManager;

    public void executeBatchJob() {
        final BatchJob<String> batchJob = new BatchJob<>();

        final List<String> elements = new ArrayList<>();
        final Iterator<String> iterator = elements.iterator();

        batchJob.read(iterator::next)
                .process(String::length)
                .write(entityManager::persist)
                .execute();
    }

    private static class BatchJob<T> {
        BatchJob<T> read(final ItemReader<? super T> itemReader) {
            return this;
        }

        <C> BatchJob<C> process(final ItemProcessor<? super T, ? super C> processor) {
            return new BatchJob<>();
        }

        BatchJob<T> write(final ItemWriter<? super T> itemWriter) {
            return this;
        }

        void execute() {}
    }

    private interface ItemReader<I> {
        I readItem() throws Exception;
    }

    private interface ItemProcessor<I, C> {
        C processItem(I item) throws Exception;
    }

    private interface ItemWriter<I> {
        void writeItems(List<I> items) throws Exception;
    }
}
