package com.intuit.transactionreconciliation.core.exceptions;

/**
 *
 */
public class ReconciliationException extends RuntimeException
{
    public ReconciliationException(String message)
    {
        super(message);
    }

    public ReconciliationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
