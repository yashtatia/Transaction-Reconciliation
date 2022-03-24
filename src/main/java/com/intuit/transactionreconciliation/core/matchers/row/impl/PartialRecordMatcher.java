package com.intuit.transactionreconciliation.core.matchers.row.impl;

import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import com.intuit.transactionreconciliation.core.exceptions.HeaderNotFoundException;
import com.intuit.transactionreconciliation.core.exceptions.ReconciliationException;
import com.intuit.transactionreconciliation.core.matchers.field.FieldMatcher;
import com.intuit.transactionreconciliation.core.matchers.row.RecordMatcher;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.RecordPairResult;
import com.intuit.transactionreconciliation.core.strategies.score.SimilarityScoreAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Order(2)
@Component
@RequiredArgsConstructor
public class PartialRecordMatcher implements RecordMatcher
{
    private final List<FieldMatcher> fieldMatchers;
    private final SimilarityScoreAggregator similarityScoreAggregator;

    @Value("${threshold.record.partial}")
    private double threshold;

    @Override
    public RecordPairResult matches(Record record1, Record record2, RecordPairResult result)
    {
        try
        {
            List<Double> similarityScores = new ArrayList<>();
            record1.getHeaders().keySet().parallelStream().forEach(key ->
            {
                String value1 = record1.get(key);
                String value2 = record2.get(key);
                Optional<FieldMatcher> fieldMatcher = fieldMatchers.stream().filter(field -> field.supports(value1, value2)).findFirst();
                fieldMatcher.ifPresent(matcher -> similarityScores.add(matcher.similarity(key, value1, value2)));
            });
            double similarityScore = similarityScoreAggregator.similarity(similarityScores);
            return Double.compare(similarityScore, threshold) >= 0 && Double.compare(similarityScore, result.getSimilarity()) > 0 ?
                    new RecordPairResult(record1, record2, CategoryEnum.PARTIAL, similarityScore) : result;
        }
        catch (HeaderNotFoundException e)
        {
            throw new ReconciliationException(e.getMessage(), e);
        }
    }
}
