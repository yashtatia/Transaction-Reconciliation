package com.intuit.transactionreconciliation.core.services.impl;

import com.intuit.transactionreconciliation.core.filters.FilterProcessor;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.modifiers.ModifierProcessor;
import com.intuit.transactionreconciliation.core.services.PreprocessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class RecordPreprocessingService implements PreprocessingService
{
    private final FilterProcessor filterProcessor;
    private final ModifierProcessor modifierProcessor;

    @Override
    public List<Record> preprocess(List<Record> records)
    {
        return modifierProcessor.modify(filterProcessor.filter(records));
    }
}
