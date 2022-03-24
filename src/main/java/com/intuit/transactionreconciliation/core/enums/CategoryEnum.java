package com.intuit.transactionreconciliation.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@Getter
@RequiredArgsConstructor
public enum CategoryEnum
{
    EXACT(1), PARTIAL(2), ONLY_IN_BUYER(3), ONLY_IN_SUPPLIER(4), NONE(Integer.MAX_VALUE);

    private final int priority;

}
