package com.intuit.transactionreconciliation.core.models;

import com.intuit.transactionreconciliation.core.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RecordPairResult
{
    private final Record record1;
    private final Record record2;
    private final CategoryEnum categoryEnum;
    private double similarity = 0d;
}
