package com.radcortez.javaee.java8.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Roberto Cortez
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(final LocalDateTime attribute) {
        return Optional.ofNullable(attribute)
                       .map(Timestamp::valueOf)
                       .orElse(null);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(final Timestamp dbData) {
        return Optional.ofNullable(dbData)
                       .map(Timestamp::toLocalDateTime)
                       .orElse(null);
    }
}
