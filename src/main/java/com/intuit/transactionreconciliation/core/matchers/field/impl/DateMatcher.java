package com.intuit.transactionreconciliation.core.matchers.field.impl;

import com.intuit.transactionreconciliation.core.matchers.field.FieldMatcher;
import com.intuit.transactionreconciliation.core.parser.FieldParser;
import com.intuit.transactionreconciliation.core.validators.impl.DateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 *
 */
@Order(1)
@Component
@RequiredArgsConstructor
public class DateMatcher implements FieldMatcher
{
    private final FieldParser<LocalDate> dateParser;
    private final DateValidator dateValidator;

    @Value("${threshold.field.date.days}")
    private int threshold;

    @Override
    public double similarity(String columnName, String record1Field, String record2Field)
    {
        LocalDate valueOne = dateParser.parse(record1Field);
        LocalDate valueTwo = dateParser.parse(record1Field);
        return valueTwo.isAfter(valueOne.minusDays(threshold)) && valueTwo.isBefore(valueOne.plusDays(threshold)) ? 100.0 : 0.0;
    }

    @Override
    public boolean equality(String columnName, String record1Field, String record2Field)
    {
        LocalDate valueOne = dateParser.parse(record1Field);
        LocalDate valueTwo = dateParser.parse(record1Field);
        return valueOne.equals(valueTwo);
    }

    @Override
    public boolean supports(String record1Field, String record2Field)
    {
        return dateValidator.isValid(record1Field) && dateValidator.isValid(record1Field);
    }
}
