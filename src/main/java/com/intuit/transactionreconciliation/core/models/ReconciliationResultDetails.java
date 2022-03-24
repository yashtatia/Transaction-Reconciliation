package com.intuit.transactionreconciliation.core.models;

import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 *
 */
@AllArgsConstructor
public class ReconciliationResultDetails
{
    private List<String> record1Values;
    private List<String> record2Values;
    private CategoryEnum category;

    public List<String> getRecord1Values()
    {
        return Collections.unmodifiableList(record1Values);
    }

    public List<String> getRecord2Values()
    {
        return Collections.unmodifiableList(record2Values);
    }

    public CategoryEnum getCategory()
    {
        return category;
    }
}
