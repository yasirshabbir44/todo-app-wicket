package com.equitativa.util;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements IConverter<LocalDate> {

    private static final long serialVersionUID = 1L;

    private static final String PATTERN = "dd MMM yyyy ";// Customize the pattern if needed

    @Override
    public LocalDate convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(PATTERN));
        } catch (Exception e) {
            throw new ConversionException("Error converting to LocalDate", e);
        }
    }

    @Override
    public String convertToString(LocalDate value, Locale locale) {
        if (value == null) {
            return null;
        }

        return value.format(DateTimeFormatter.ofPattern(PATTERN));
    }

    public String convertToString(Instant value) {
        LocalDate localDate = LocalDate.ofInstant(value, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        // Format LocalDateTime to a string using the custom formatter
        return localDate.format(formatter);
    }

}
