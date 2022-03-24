package com.intuit.transactionreconciliation.core.services;

import java.io.File;

/**
 *
 */
public interface ReconciliationService<T>
{
    T reconcile(File file1, File file2);
}
