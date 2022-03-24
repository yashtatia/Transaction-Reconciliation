package com.intuit.transactionreconciliation.core.matchers.field.impl;

import com.intuit.transactionreconciliation.core.matchers.field.FieldMatcher;
import com.intuit.transactionreconciliation.core.strategies.similarity.StringSimilarityStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 */
@Order(3)
@Component
@RequiredArgsConstructor
public class StringMatcher implements FieldMatcher
{
    private final StringSimilarityStrategy stringSimilarityStrategy;

    @Value("${threshold.field.string.percentage}")
    private Double threshold;

    @Override
    public double similarity(String columnName, String record1Field, String record2Field)
    {
        double similarity = stringSimilarityStrategy.similarity(record1Field, record2Field);
        return similarity >= threshold ? similarity : 0.0;
    }

    @Override
    public boolean equality(String columnName, String record1Field, String record2Field)
    {
        return record1Field.equals(record2Field);
    }

    @Override
    public boolean supports(String record1Field, String record2Field)
    {
        return record1Field != null && record2Field != null;
    }
}
