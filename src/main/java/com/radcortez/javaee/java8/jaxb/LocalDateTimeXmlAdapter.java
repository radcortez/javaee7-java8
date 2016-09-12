package com.radcortez.javaee.java8.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(final String value) {
        return Optional.ofNullable(value)
                       .map(v -> LocalDateTime.parse(v, ISO_DATE_TIME))
                       .orElse(null);
    }

    @Override
    public String marshal(final LocalDateTime dateTime) {
        return Optional.ofNullable(dateTime)
                       .map(dt -> dateTime.format(ISO_DATE_TIME))
                       .orElse(null);
    }
}
