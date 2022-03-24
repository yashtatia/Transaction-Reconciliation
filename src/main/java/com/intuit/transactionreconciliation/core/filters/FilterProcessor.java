package com.intuit.transactionreconciliation.core.filters;

import com.intuit.transactionreconciliation.core.models.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class FilterProcessor
{
    private final List<Filter> filters;

    public List<Record> filter(List<Record> records)
    {
        List<Record> filteredRecords = records;
        for (Filter filter : filters)
        {
            filteredRecords = filter.apply(filteredRecords);
        }

        return filteredRecords;
    }
}
