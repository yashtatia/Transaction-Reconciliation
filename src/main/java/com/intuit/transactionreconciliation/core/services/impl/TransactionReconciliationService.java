package com.intuit.transactionreconciliation.core.services.impl;

import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import com.intuit.transactionreconciliation.core.matchers.row.RecordMatcher;
import com.intuit.transactionreconciliation.core.models.EmptyRecord;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.RecordPairResult;
import com.intuit.transactionreconciliation.core.services.PreprocessingService;
import com.intuit.transactionreconciliation.core.services.ReconciliationService;
import com.intuit.transactionreconciliation.core.services.StorageService;
import com.intuit.transactionreconciliation.core.strategies.reconcilation.ReconciliationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class TransactionReconciliationService<T> implements ReconciliationService<T>
{
    private final List<RecordMatcher> recordMatchers;
    private final ReconciliationStrategy<T> reconciliationStrategy;
    private final PreprocessingService preprocessingService;
    private final StorageService storageService;

    @Override
    public T reconcile(File file1, File file2)
    {
        Iterable<Record> records1 = preprocessingService.preprocess(storageService.loadRecords(file1));
        Iterable<Record> records2 = preprocessingService.preprocess(storageService.loadRecords(file2));
        List<RecordPairResult> results = new ArrayList<>();
        records1.forEach(record1 ->
        {
            RecordPairResult result = null;
            for (Record record2 : records2)
            {
                result = new RecordPairResult(record1, record2, CategoryEnum.NONE);
                for (RecordMatcher recordMatcher : recordMatchers)
                {
                    result = recordMatcher.matches(record1, record2, result);
                }
                results.add(result);
            }

            if (result == null)
            {
                results.add(new RecordPairResult(record1, new EmptyRecord(record1.getHeaders()), CategoryEnum.NONE));
            }
        });
        return reconciliationStrategy.reconcile(results);
    }
}
