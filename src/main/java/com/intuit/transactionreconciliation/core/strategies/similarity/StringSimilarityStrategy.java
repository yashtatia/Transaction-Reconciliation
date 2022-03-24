package com.intuit.transactionreconciliation.core.strategies.similarity;

/**
 *
 */
@FunctionalInterface
public interface StringSimilarityStrategy
{
    double similarity(String value1, String value2);
}
