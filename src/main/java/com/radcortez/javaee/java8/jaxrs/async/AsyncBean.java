package com.radcortez.javaee.java8.jaxrs.async;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Roberto Cortez
 */
@ApplicationPath("/resources")
@Path("async")
public class AsyncBean extends Application{
    @Resource
    private ManagedExecutorService executor;

    @GET
    public void expensiveCall(@Suspended final AsyncResponse asyncResponse) {
        CompletableFuture.supplyAsync(this::longExecution, executor)
                         .thenApplyAsync(duration -> duration + longExecution())
                         .thenApply(duration -> "It took me " + duration + " seconds to generate this response!")
                         .thenAccept(asyncResponse::resume);
    }

    private int longExecution() {
        try {
            int random = (int) (Math.random() * 10L);
            SECONDS.sleep(random);
            return random;
        } catch (InterruptedException e) {
            return 0;
        }
    }
}
