package com.intuit.transactionreconciliation.core.services.impl;

import com.intuit.transactionreconciliation.core.exceptions.ReconciliationException;
import com.intuit.transactionreconciliation.core.models.Record;
import com.intuit.transactionreconciliation.core.models.SingularReconciliationResult;
import com.intuit.transactionreconciliation.core.services.StorageService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class FileSystemStorageService implements StorageService<SingularReconciliationResult>
{
    @Value("${csv.field.separator}")
    private char SEPARATOR;

    @Value("${storage.directory.local}")
    private String directory;

    @Override
    public void store(SingularReconciliationResult result)
    {
        File file = new File(directory + "Result.csv");
        try
        {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            List<String> header = new ArrayList<>(result.getHeaders());
            header.add("Category");
            header.addAll(result.getHeaders());
            writer.writeNext(header.toArray(new String[0]));

            result.getDetails().forEach(details -> {
                List<String> data = new ArrayList<>(details.getRecord1Values());
                data.add(details.getCategory().name());
                data.addAll(details.getRecord2Values());
                writer.writeNext(data.toArray(new String[0]));
            });

            // closing writer connection
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> loadRecords(File file)
    {
        List<Record> records = new ArrayList<>();
        try
        {
            FileReader filereader = new FileReader(file);
            CSVParser parser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = csvReader.readAll();
            AtomicInteger colNumber = new AtomicInteger(0);
            Map<String, Integer> headers = Arrays.stream(allData.get(0)).collect(Collectors.toMap(Function.identity(), s -> colNumber.getAndIncrement()));
            allData.remove(0);
            AtomicInteger rowNumber = new AtomicInteger(0);
            for (String[] row : allData)
            {
                records.add(new Record(rowNumber.incrementAndGet(), Arrays.stream(row).collect(Collectors.toList()), headers));
            }
        }
        catch (IOException | CsvException e)
        {
            throw new ReconciliationException(e.getMessage(), e);
        }
        return records;
    }
}
