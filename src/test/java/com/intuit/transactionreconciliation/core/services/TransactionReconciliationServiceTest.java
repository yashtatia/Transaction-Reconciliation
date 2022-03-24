package com.intuit.transactionreconciliation.core.services;

import com.intuit.transactionreconciliation.Application;
import com.intuit.transactionreconciliation.core.models.SingularReconciliationResult;
import com.intuit.transactionreconciliation.core.services.impl.TransactionReconciliationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class TransactionReconciliationServiceTest
{
    @Autowired
    private TransactionReconciliationService<SingularReconciliationResult> transactionReconciliationService;

    @Autowired
    private StorageService<SingularReconciliationResult> storageService;

    @Test
    public void testReconciliation()
    {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        File buyer = resourceDirectory.resolve("Buyer.csv").toFile();
        File supplier = resourceDirectory.resolve("Supplier.csv").toFile();
        SingularReconciliationResult result = transactionReconciliationService.reconcile(buyer, supplier);
        storageService.store(result);
    }
}
