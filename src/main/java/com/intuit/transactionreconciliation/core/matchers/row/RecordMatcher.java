package com.intuit.transactionreconciliation.core.matchers.row;

import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.RecordPairResult;

/**
 *
 */
@FunctionalInterface
public interface RecordMatcher
{
    RecordPairResult matches(Record record1, Record record2, RecordPairResult result);
}
