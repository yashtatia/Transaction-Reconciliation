package com.intuit.transactionreconciliation.core.strategies.reconcilation.impl;

import com.intuit.transactionreconciliation.core.comparator.RecordPairComparator;
import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import com.intuit.transactionreconciliation.core.models.EmptyRecord;
import com.intuit.transactionreconciliation.core.models.ReconciliationResultDetails;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.RecordPairResult;
import com.intuit.transactionreconciliation.core.models.SingularReconciliationResult;
import com.intuit.transactionreconciliation.core.strategies.reconcilation.ReconciliationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class SingularReconciliationStrategy implements ReconciliationStrategy<SingularReconciliationResult>
{
    private final RecordPairComparator recordPairComparator;

    @Override
    public SingularReconciliationResult reconcile(List<RecordPairResult> results)
    {
        Map<Record, List<RecordPairResult>> group = results.stream().collect(Collectors.groupingBy(RecordPairResult::getRecord1));
        List<RecordPairResult> pairResults = new ArrayList<>();
        Set<Record> record = new HashSet<>();
        if (!group.isEmpty())
        {
            record.addAll(group.values().iterator().next().stream().map(RecordPairResult::getRecord2).collect(Collectors.toSet()));
        }

        group.forEach((k, v) -> {
            v.sort(recordPairComparator);
            pairResults.add(CategoryEnum.NONE.equals(v.get(0).getCategoryEnum()) ? new RecordPairResult(k, new EmptyRecord(k.getHeaders()), CategoryEnum.ONLY_IN_BUYER) : v.get(0));
        });

        record.removeAll(pairResults.stream().map(RecordPairResult::getRecord2).collect(Collectors.toSet()));

        record.forEach(r -> pairResults.add(new RecordPairResult(new EmptyRecord(r.getHeaders()), r, CategoryEnum.ONLY_IN_SUPPLIER)));
        pairResults.sort(Comparator.comparingInt(p -> p.getRecord1().getRowNumber()));

        List<ReconciliationResultDetails> details = new ArrayList<>();
        pairResults.forEach(result -> details.add(new ReconciliationResultDetails(result.getRecord1().getValues(), result.getRecord2().getValues(), result.getCategoryEnum())));
        List<String> headers = pairResults.get(0).getRecord1().getHeaders().entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).map(Map.Entry::getKey).collect(Collectors.toList());
        return new SingularReconciliationResult(headers, details);
    }
}
