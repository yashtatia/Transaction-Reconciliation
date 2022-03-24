package com.intuit.transactionreconciliation.core.models;

import com.intuit.transactionreconciliation.core.exceptions.HeaderNotFoundException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Getter
public class EmptyRecord extends Record
{
    public EmptyRecord(Map<String, Integer> headers)
    {
        super(Integer.MAX_VALUE, null, headers);
    }

    @Override
    public String get(String headerName) throws HeaderNotFoundException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getValues()
    {
        String[] values = new String[headers.size()];
        Arrays.fill(values, "");
        return Arrays.stream(values).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString()
    {
        return StringUtils.EMPTY;
    }
}
