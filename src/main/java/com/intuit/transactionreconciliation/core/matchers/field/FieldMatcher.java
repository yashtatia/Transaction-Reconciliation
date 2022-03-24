package com.intuit.transactionreconciliation.core.matchers.field;

/**
 *
 */
public interface FieldMatcher
{
    double similarity(String columnName, String record1Field, String record2Field);

    boolean equality(String columnName, String record1Field, String record2Field);

    boolean supports(String record1Field, String record2Field);
}
