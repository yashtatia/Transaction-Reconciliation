package com.intuit.transactionreconciliation.core.parser;

/**
 *
 */
@FunctionalInterface
public interface FieldParser<T>
{
    T parse(String value);
}
