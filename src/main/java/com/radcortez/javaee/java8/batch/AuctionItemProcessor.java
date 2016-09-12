package com.radcortez.javaee.java8.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import java.util.Optional;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@Named
public class AuctionItemProcessor implements ItemProcessor {
    @Override
    public Object processItem(final Object item) throws Exception {
        System.out.println("AuctionItemProcessor.processItem");
        // Usual Way
        final String line = (String) item;
        final String[] values = line.split(",");
        final Auction auction = new Auction(null,
                                            values[1],
                                            Long.valueOf(values[2]),
                                            Long.valueOf(values[3]),
                                            Integer.valueOf(values[4]));

        // Enhanced Way
        return Optional.of(item)
                       .map(String.class::cast)
                       .map(s -> s.split(","))
                       .map(a -> new Auction(null,
                                             a[1],
                                             Long.valueOf(a[2]),
                                             Long.valueOf(a[3]),
                                             Integer.valueOf(a[4])))
                       .get();
    }
}
