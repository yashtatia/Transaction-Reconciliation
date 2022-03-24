package com.intuit.transactionreconciliation.core.parser.impl;

import com.intuit.transactionreconciliation.core.parser.FieldParser;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UnknownFormatConversionException;

/**
 *
 */
@Component
public class DateParser implements FieldParser<LocalDate>
{
    @Value("${supported.date.formats}")
    private String supportedDateFormats;

    public LocalDate parse(String value)
    {
        String[] dateFormats = supportedDateFormats.split(",");
        for (String dateFormat : dateFormats)
        {
            try
            {
                return LocalDate.parse(value, DateTimeFormatter.ofPattern(dateFormat));
            }
            catch (DateTimeParseException e)
            {
                throw new UnknownFormatConversionException(e.getMessage());
            }
        }
        throw new UnknownFormatConversionException("No supported date formats found. Please check configuration.");
    }
}
