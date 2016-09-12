package com.radcortez.javaee.java8.batch;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@Named
public class AuctionItemWriter extends AbstractItemWriter {
    public static Long id = 0L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void writeItems(final List<Object> items) throws Exception {
        // Usual Way
        for (final Object item : items) {
            final Auction auction = (Auction) item;
            auction.setId(id++);
            entityManager.persist(auction);
        }

        // Enhanced Way
        items.stream()
             .map(Auction.class::cast)
             .forEach(entityManager::persist);
    }
}
