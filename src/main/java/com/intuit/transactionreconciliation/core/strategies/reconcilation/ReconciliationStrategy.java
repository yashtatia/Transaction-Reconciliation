package com.intuit.transactionreconciliation.core.strategies.reconcilation;

import com.intuit.transactionreconciliation.core.models.RecordPairResult;

import java.util.List;

/**
 *
 */
@FunctionalInterface
public interface ReconciliationStrategy<T>
{
    T reconcile(List<RecordPairResult> results);
}
