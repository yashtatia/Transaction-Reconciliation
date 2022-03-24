package com.intuit.transactionreconciliation.web.controllers;

import com.intuit.transactionreconciliation.core.exceptions.ReconciliationException;
import com.intuit.transactionreconciliation.core.models.SingularReconciliationResult;
import com.intuit.transactionreconciliation.core.services.impl.TransactionReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 *
 */
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TransactionReconciliationController
{
    private final TransactionReconciliationService<SingularReconciliationResult> transactionReconciliationService;

    @PostMapping(path = "/reconcile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"multipart/form-data"})
    @ResponseBody
    public SingularReconciliationResult reconcile(@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2)
    {
        try
        {
            return transactionReconciliationService.reconcile(convert(file1), convert(file2));
        }
        catch (IllegalArgumentException | IOException e)
        {
            throw new ReconciliationException(e.getMessage(), e);
        }
    }

    public static File convert(MultipartFile file) throws IOException
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
