package com.intuit.transactionreconciliation.core.validators;

/**
 *
 */
@FunctionalInterface
public interface Validator
{
    boolean isValid(String value);
}
