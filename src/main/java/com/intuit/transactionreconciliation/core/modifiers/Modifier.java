package com.intuit.transactionreconciliation.core.modifiers;

import com.intuit.transactionreconciliation.core.models.Record;

import java.util.List;

/**
 *
 */
@FunctionalInterface
public interface Modifier
{
    List<Record> apply(List<Record> records);
}
