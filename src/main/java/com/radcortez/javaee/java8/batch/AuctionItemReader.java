package com.radcortez.javaee.java8.batch;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@Named
public class AuctionItemReader extends AbstractItemReader {
    private BufferedReader bufferedReader;

    @Override
    public void open(final Serializable checkpoint) throws Exception {
        bufferedReader = new BufferedReader(new InputStreamReader(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResourceAsStream("/META-INF/batch-jobs/auctions.csv")));
    }

    @Override
    public Object readItem() throws Exception {
        System.out.println("AuctionItemReader.readItem");
        return bufferedReader.readLine();
    }
}
