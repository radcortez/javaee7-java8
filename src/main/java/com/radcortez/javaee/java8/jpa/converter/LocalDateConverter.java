package com.radcortez.javaee.java8.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Roberto Cortez
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(final LocalDate attribute) {
        return Optional.ofNullable(attribute)
                       .map(localDate -> Timestamp.valueOf(localDate.atStartOfDay()))
                       .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(final Timestamp dbData) {
        return Optional.ofNullable(dbData)
                       .map(Timestamp::toLocalDateTime)
                       .map(LocalDateTime::toLocalDate)
                       .orElse(null);
    }
}
