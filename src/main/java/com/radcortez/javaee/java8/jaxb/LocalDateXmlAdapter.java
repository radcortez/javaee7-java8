package com.radcortez.javaee.java8.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(final String value) {
        return Optional.ofNullable(value)
                       .map(v -> LocalDate.parse(v, ISO_DATE))
                       .orElse(null);
    }

    @Override
    public String marshal(final LocalDate date) {
        return Optional.ofNullable(date)
                       .map(dt -> date.format(ISO_DATE))
                       .orElse(null);
    }
}
