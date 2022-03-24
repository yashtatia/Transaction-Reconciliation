package com.intuit.transactionreconciliation.core.strategies.score;

import java.util.List;

/**
 *
 */
@FunctionalInterface
public interface SimilarityScoreAggregator
{
    double similarity(List<Double> similarityScores);
}
