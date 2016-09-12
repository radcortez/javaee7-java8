package com.radcortez.javaee.java8.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZonedDateTime;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
public class ZonedDateTimeXmlAdapter extends XmlAdapter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime unmarshal(final String value) {
        return Optional.ofNullable(value)
                       .map(v -> ZonedDateTime.parse(v, ISO_DATE_TIME).withFixedOffsetZone())
                       .orElse(null);
    }

    @Override
    public String marshal(final ZonedDateTime dateTime) {
        return Optional.ofNullable(dateTime)
                       .map(dt -> dateTime.withFixedOffsetZone().format(ISO_DATE_TIME))
                       .orElse(null);
    }
}
