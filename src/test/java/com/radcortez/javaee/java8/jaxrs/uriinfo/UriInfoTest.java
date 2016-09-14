package com.radcortez.javaee.java8.jaxrs.uriinfo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class UriInfoTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.jaxrs.uriinfo")
                         .addAsWebInfResource("beans.xml");
    }

    @ArquillianResource
    private URL base;

    @Test
    public void testFindAddress() throws Exception {
        System.out.println("base = " + base);

        final Client client = ClientBuilder.newClient();
        final Address address = client.target(URI.create(new URL(base, "resources/uriinfo/find").toExternalForm()))
                                      .queryParam("street", "Ninja Road")
                                      .queryParam("city", "Konoha")
                                      .queryParam("postalCode", "11111")
                                      .queryParam("country", "Land of Fire")
                                      .request(APPLICATION_JSON)
                                      .get(Address.class);

        assertNotNull(address);
    }

    @Test
    public void testFindAddressEnhanced() throws Exception {
        System.out.println("base = " + base);

        final Client client = ClientBuilder.newClient();
        final Address address = client.target(URI.create(new URL(base, "resources/uriinfo/enhanced").toExternalForm()))
                                      .queryParam("street", "Ninja Road")
                                      .queryParam("city", "Konoha")
                                      .queryParam("postalCode", "11111")
                                      .queryParam("country", "Land of Fire")
                                      .request(APPLICATION_JSON)
                                      .get(Address.class);

        assertNotNull(address);
    }

    @Test
    public void testToParameterList() throws Exception {
        final List<String> parameterList = UriInfoBean.toParameterList("1,2,3", "0");
        parameterList.forEach(System.out::println);

        final List<String> parameterListEnhanced = UriInfoBean.toParameterListEnhanced("1,2,3", "0");
        parameterListEnhanced.forEach(System.out::println);
    }
}
