package de.supercode.superbnb.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class YearMonthConverter implements AttributeConverter<YearMonth, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yy/MM");

    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
        if (yearMonth != null) {
            return yearMonth.format(FORMATTER);
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isEmpty()) {
            return YearMonth.parse(dbData, FORMATTER);
        }
        return null;
    }
}