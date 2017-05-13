package cz.tul.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Petr on 13.05.2017.
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime,Timestamp> {
// pro pouziti prevodu casoveho formatu se v datovem modelu pouzije anotace "@Convert(converter = LocalDateTimeConverter.class)"

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
