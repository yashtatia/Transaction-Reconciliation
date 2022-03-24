package com.intuit.transactionreconciliation.core.strategies.score.impl;

import com.intuit.transactionreconciliation.core.strategies.score.SimilarityScoreAggregator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class AverageSimilarityScoreAggregator implements SimilarityScoreAggregator
{
    @Override
    public double similarity(List<Double> similarityScores)
    {
        return similarityScores.stream().mapToDouble(a -> a).average().orElse(0.0);
    }
}
