package com.intuit.transactionreconciliation.core.models;

import com.intuit.transactionreconciliation.core.exceptions.HeaderNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Getter
@ToString
@EqualsAndHashCode(doNotUseGetters = true)
public class Record
{
    @ToString.Exclude
    final Map<String, Integer> headers;
    final int rowNumber;
    final List<String> values;

    public Record(int rowNumber, List<String> values, final Map<String, Integer> headers)
    {
        if (rowNumber < 0)
        {
            throw new IllegalArgumentException("Row Number cannot be less than 0.");
        }
        this.rowNumber = rowNumber;
        this.values = values != null ? Collections.unmodifiableList(values) : Collections.emptyList();
        this.headers = headers != null ? Collections.unmodifiableMap(headers) : Collections.emptyMap();
    }

    public String get(String headerName) throws HeaderNotFoundException
    {
        final Integer index = headers.get(headerName);
        if (index == null)
        {
            throw new HeaderNotFoundException(String.format("Mapping for '%s' not found, expected one of '%s'", headerName, headers.keySet()));
        }
        try
        {
            return values.get(index);
        }
        catch (final ArrayIndexOutOfBoundsException e)
        {
            throw new IllegalStateException(String.format("Index for header '%s' is %d but this record has only %d values", headerName, index, values.size()), e);
        }
    }
}
