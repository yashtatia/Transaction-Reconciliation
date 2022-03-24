package com.intuit.transactionreconciliation.core.filters;

import com.intuit.transactionreconciliation.core.models.Record;

import java.util.List;

/**
 *
 */
public interface Filter
{
    List<Record> apply(List<Record> records);
}
