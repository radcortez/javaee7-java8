package com.radcortez.javaee.java8.jaxrs.async;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URL;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class AsyncBeanTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.jaxrs.async")
                         .addAsWebInfResource("beans.xml");
    }

    @ArquillianResource
    private URL base;

    @Test
    public void testAsync() throws Exception {
        final Client client = ClientBuilder.newClient();
        final String response = client.target(URI.create(new URL(base, "resources/async").toExternalForm()))
                                      .request(MediaType.TEXT_PLAIN_TYPE)
                                      .get(String.class);
        System.out.println(response);
    }
}
