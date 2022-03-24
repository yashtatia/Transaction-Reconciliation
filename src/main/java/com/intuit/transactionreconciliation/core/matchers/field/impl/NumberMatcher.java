package com.intuit.transactionreconciliation.core.matchers.field.impl;

import com.intuit.transactionreconciliation.core.matchers.field.FieldMatcher;
import com.intuit.transactionreconciliation.core.validators.impl.NumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 */
@Order(2)
@Component
@RequiredArgsConstructor
public class NumberMatcher implements FieldMatcher
{
    private final NumberValidator numberValidator;

    @Value("${threshold.field.number}")
    private double threshold;

    @Override
    public double similarity(String columnName, String record1Field, String record2Field)
    {
        double valueOne = Double.parseDouble(record1Field.replace(",", ""));
        double valueTwo = Double.parseDouble(record2Field.replace(",", ""));
        return Double.compare(valueTwo, valueOne - threshold) >= 0 && Double.compare(valueTwo, valueOne + threshold) <= 0 ? 100.0 : 0.0;
    }

    @Override
    public boolean equality(String columnName, String record1Field, String record2Field)
    {
        double valueOne = Double.parseDouble(record1Field.replace(",", ""));
        double valueTwo = Double.parseDouble(record2Field.replace(",", ""));
        return Double.compare(valueOne, valueTwo) == 0;
    }

    @Override
    public boolean supports(String record1Field, String record2Field)
    {
        return numberValidator.isValid(record1Field) && numberValidator.isValid(record2Field);
    }
}
