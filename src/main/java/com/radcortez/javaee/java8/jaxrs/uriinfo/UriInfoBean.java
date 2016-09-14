package com.radcortez.javaee.java8.jaxrs.uriinfo;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Roberto Cortez
 */
@ApplicationPath("/resources")
@Path("uriinfo")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class UriInfoBean extends Application {
    private final List<Address> addresses = new ArrayList<>();

    @PostConstruct
    public void init() {
        addresses.add(new Address("Ninja Road", "Konoha", "11111", "Land of Fire"));
        addresses.add(new Address("Windy Road", "Sunagakure", "22222", "Land of Wind"));
        addresses.add(new Address("Missing", "Unknown", "33333", "Mistery"));
    }

    @GET
    @Path("/find")
    public Address findAddress(@Context final UriInfo uriInfo) {
        final String street = uriInfo.getQueryParameters().getFirst("street");
        final String city = uriInfo.getQueryParameters().getFirst("city");
        final String postalCode = uriInfo.getQueryParameters().getFirst("postalCode");
        final String country = uriInfo.getQueryParameters().getFirst("country");

        for (Address address : addresses) {
            if (address.getStreet().equals(street != null ? street : address.getStreet()) &&
                address.getCity().equals(city != null ? city : address.getCity()) &&
                address.getPostalCode().equals(postalCode != null ? postalCode : address.getPostalCode()) &&
                address.getCountry().equals(country != null ? country : address.getCountry())) {
                return address;
            }
        }

        throw new WebApplicationException();
    }

    @GET
    @Path("/enhanced")
    public Address findAddressEnhanced(@Context final UriInfo uriInfo) {
        final Optional<String> street = Optional.ofNullable(uriInfo.getQueryParameters().getFirst("street"));
        final Optional<String> city = Optional.ofNullable(uriInfo.getQueryParameters().getFirst("city"));
        final Optional<String> postalCode = Optional.ofNullable(uriInfo.getQueryParameters().getFirst("postalCode"));
        final Optional<String> country = Optional.ofNullable(uriInfo.getQueryParameters().getFirst("country"));

        return addresses.stream()
                        .filter(address -> address.getStreet().equals(street.orElse(address.getStreet())))
                        .filter(address -> address.getCity().equals(city.orElse(address.getCity())))
                        .filter(address -> address.getPostalCode().equals(postalCode.orElse(address.getPostalCode())))
                        .filter(address -> address.getCountry().equals(country.orElse(address.getCountry())))
                        .findFirst()
                        .orElseThrow(WebApplicationException::new);
    }

    public static List<String> toParameterList(final String params, final String defaultValue) {
        if (params != null) {
            final List<String> parsedParams = new ArrayList<>();
            for (final String p : params.split(",")) {
                parsedParams.add(p.toUpperCase());
            }
            return parsedParams;
        } else {
            return Collections.singletonList(defaultValue);
        }
    }

    public static List<String> toParameterListEnhanced(final String params, final String defaultValue) {
        return Optional.ofNullable(params)
                       .map(p -> p.split(","))
                       .map(a -> Stream.of(a)
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList()))
                       .orElse(Collections.singletonList(defaultValue));
    }
}

