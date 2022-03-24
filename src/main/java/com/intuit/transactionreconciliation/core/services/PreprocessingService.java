package com.intuit.transactionreconciliation.core.services;

import com.intuit.transactionreconciliation.core.models.Record;

import java.util.List;

/**
 *
 */
@FunctionalInterface
public interface PreprocessingService
{
    List<Record> preprocess(List<Record> records);
}
