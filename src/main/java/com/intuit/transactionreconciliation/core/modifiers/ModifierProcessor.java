package com.intuit.transactionreconciliation.core.modifiers;

import com.intuit.transactionreconciliation.core.filters.Filter;
import com.intuit.transactionreconciliation.core.models.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class ModifierProcessor
{
    private final List<Modifier> modifiers;

    public List<Record> modify(List<Record> records)
    {
        List<Record> modifiedRecords = records;
        for (Modifier modifier : modifiers)
        {
            modifiedRecords = modifier.apply(modifiedRecords);
        }

        return modifiedRecords;
    }
}
