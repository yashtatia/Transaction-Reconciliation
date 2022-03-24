package com.intuit.transactionreconciliation.core.matchers.row.impl;

import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import com.intuit.transactionreconciliation.core.exceptions.HeaderNotFoundException;
import com.intuit.transactionreconciliation.core.exceptions.ReconciliationException;
import com.intuit.transactionreconciliation.core.matchers.field.FieldMatcher;
import com.intuit.transactionreconciliation.core.matchers.row.RecordMatcher;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.RecordPairResult;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Order(1)
@Component
@RequiredArgsConstructor
public class ExactRecordMatcher implements RecordMatcher
{
    private final List<FieldMatcher> fieldMatchers;

    @Override
    public RecordPairResult matches(Record record1, Record record2, RecordPairResult result)
    {
        if (record1.equals(record2))
        {
            return new RecordPairResult(record1, record2, CategoryEnum.EXACT, 100);
        }

        try
        {
            for (String key : record1.getHeaders().keySet())
            {
                String value1 = record1.get(key);
                String value2 = record2.get(key);
                Optional<FieldMatcher> fieldMatcher = fieldMatchers.stream().filter(field -> field.supports(value1, value2)).findFirst();
                if (fieldMatcher.isPresent() && !fieldMatcher.get().equality(key, value1, value2))
                {
                    return result;
                }
            }
            return new RecordPairResult(record1, record2, CategoryEnum.EXACT, 100);
        }
        catch (HeaderNotFoundException e)
        {
            throw new ReconciliationException(e.getMessage(), e);
        }
    }
}
