package com.intuit.transactionreconciliation.core.validators.impl;

import com.intuit.transactionreconciliation.core.validators.Validator;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class NumberValidator implements Validator
{
    @Override
    public boolean isValid(String value)
    {
        return NumberUtils.isParsable(value.replace(",", ""));
    }
}
