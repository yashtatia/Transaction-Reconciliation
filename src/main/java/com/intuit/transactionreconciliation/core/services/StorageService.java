package com.intuit.transactionreconciliation.core.services;

import com.intuit.transactionreconciliation.core.models.Record;

import java.io.File;
import java.util.List;

/**
 *
 */
public interface StorageService<T>
{
    void store(T result);

    List<Record> loadRecords(File file);
}
