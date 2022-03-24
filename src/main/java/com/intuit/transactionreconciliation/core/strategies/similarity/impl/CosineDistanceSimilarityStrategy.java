package com.intuit.transactionreconciliation.core.strategies.similarity.impl;

import com.intuit.transactionreconciliation.core.strategies.similarity.StringSimilarityStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.CosineDistance;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CosineDistanceSimilarityStrategy implements StringSimilarityStrategy
{
    @Override
    public double similarity(String value1, String value2)
    {
        if (StringUtils.isBlank(value1) || StringUtils.isBlank(value2))
        {
            return 0d;
        }
        double cosineDistance = new CosineDistance().apply(value1, value2);
        return Math.round((1 - cosineDistance) * 100);
    }
}
