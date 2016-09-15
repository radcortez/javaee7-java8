package com.radcortez.javaee.java8.batch;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Roberto Cortez
 */
public class BatchCrazyIdea {
    @Resource
    private DataSource dataSource;
    @PersistenceContext
    private EntityManager entityManager;

    public void executeBatchJob() throws Exception {
        final ResultSet resultSet = dataSource.getConnection()
                                              .prepareStatement("SELECT * FROM ITEM",
                                                                ResultSet.TYPE_FORWARD_ONLY,
                                                                ResultSet.CONCUR_READ_ONLY,
                                                                ResultSet.HOLD_CURSORS_OVER_COMMIT)
                                              .getResultSet();

        // Just a Lazy init to avoid implement entire Stream interface.
        final BatchJob<ResultSet> batchJob = Batch.createBatchJob(ResultSet.class);

        batchJob.itemCount(10)
                .read((() -> resultSet.next() ? resultSet : null))
                .map(rs -> getResultSetString(rs, 1))
                .sorted(Comparator.comparing(String::length))
                .write(entityManager::persist)
                .execute();
    }

    public static class Batch {
        static <E> BatchJob<E> createBatchJob(Class<E> klass) {
            return null;
        }
    }

    private interface BatchJob<E> extends Stream<E> {
        BatchJob<E> itemCount(final int itemCount);

        BatchJob<E> read(final ItemReader<? super E> itemReader) throws Exception;

        BatchJob<E> write(final ItemWriter<? super E> itermWriter) throws Exception;

        <R> BatchJob<R> map(Function<? super E, ? extends R> mapper);

        BatchJob<E> sorted(Comparator<? super E> comparator);

        void execute();
    }

    private interface ItemReader<I> {
        I readItem() throws Exception;
    }

    private interface ItemWriter<I> {
        void writeItems(I item) throws Exception;
    }

    private String getResultSetString(final ResultSet resultSet, int index) {
        try {
            return resultSet.getString(index);
        } catch (SQLException e) {
            return null;
        }
    }
}
