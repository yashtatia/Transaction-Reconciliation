package com.intuit.transactionreconciliation.core.comparator;

import com.intuit.transactionreconciliation.core.models.RecordPairResult;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 *
 */
@Component
public class RecordPairComparator implements Comparator<RecordPairResult>
{
    @Override
    public int compare(RecordPairResult o1, RecordPairResult o2)
    {
        if (o1.getCategoryEnum().getPriority() == o2.getCategoryEnum().getPriority())
        {
            return Double.compare(o2.getSimilarity(), o1.getSimilarity());
        }
        return Integer.compare(o1.getCategoryEnum().getPriority(), o2.getCategoryEnum().getPriority());
    }
}
