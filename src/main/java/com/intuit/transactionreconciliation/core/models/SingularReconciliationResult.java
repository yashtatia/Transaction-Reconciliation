package com.intuit.transactionreconciliation.core.models;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 *
 */
@AllArgsConstructor
public class SingularReconciliationResult
{
    private List<String> headers;
    private List<ReconciliationResultDetails> details;

    public List<String> getHeaders()
    {
        return Collections.unmodifiableList(headers);
    }

    public List<ReconciliationResultDetails> getDetails()
    {
        return Collections.unmodifiableList(details);
    }
}
