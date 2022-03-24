package com.intuit.transactionreconciliation.core.validators.impl;

import com.intuit.transactionreconciliation.core.parser.impl.DateParser;
import com.intuit.transactionreconciliation.core.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UnknownFormatConversionException;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class DateValidator implements Validator
{
    private final DateParser dateParser;

    @Override
    public boolean isValid(String value)
    {
        try
        {
            dateParser.parse(value);
        }
        catch (UnknownFormatConversionException e)
        {
            return false;
        }
        return true;
    }
}
