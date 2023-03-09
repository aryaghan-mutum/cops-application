package com.copsdbtest;

import com.copsdbtest.data.SupplierData;
import com.copsdbtest.dbservice.SupplierDbService;
import com.copsdbtest.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

@Slf4j
public class SupplierDbTest {

    private SupplierDto supplierDto;
    private SupplierDbService supplierDbService;

    @BeforeEach
    public void setup() {
        log.info("SupplierDbTest::setup");
        supplierDto = new SupplierData().createSupplierData();
        supplierDbService = new SupplierDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("ProductDbTest::deleteRecords");
        supplierDbService.deleteSupplier(supplierDto);
    }

    @Test
    @Description("")
    public void testSupplier() {
        log.info("ProductDbTest::testSupplier");
        supplierDbService.insertSupplier(supplierDto);
        supplierDbService.insertMultipleSuppliers(5);
    }

}
