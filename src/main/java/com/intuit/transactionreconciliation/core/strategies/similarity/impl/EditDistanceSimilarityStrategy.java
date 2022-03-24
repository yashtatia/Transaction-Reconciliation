package com.intuit.transactionreconciliation.core.strategies.similarity.impl;

import com.intuit.transactionreconciliation.core.strategies.similarity.StringSimilarityStrategy;

import static org.apache.commons.lang3.math.NumberUtils.min;

/**
 *
 */
//@Service
public class EditDistanceSimilarityStrategy implements StringSimilarityStrategy
{
    @Override
    public double similarity(String value1, String value2)
    {
        int[][] dp = new int[value1.length() + 1][value2.length() + 1];

        for (int i = 0; i <= value1.length(); i++)
        {
            for (int j = 0; j <= value2.length(); j++)
            {
                if (i == 0)
                {
                    dp[i][j] = j;
                }
                else if (j == 0)
                {
                    dp[i][j] = i;
                }
                else if (value1.charAt(i - 1) == value2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else
                {
                    dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return (1 - dp[value1.length()][value2.length()] / (value1.length() * value2.length() * 1.0)) * 100;
    }
}
