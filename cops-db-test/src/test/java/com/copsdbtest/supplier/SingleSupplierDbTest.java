package com.copsdbtest.supplier;

import com.copsdbtest.data.SupplierData;
import com.copsdbtest.dbservice.SupplierDbService;
import com.copsdbtest.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;

@Slf4j
public class SingleSupplierDbTest {
    private SupplierDbService supplierDbService;
    private SupplierDto supplierDto;

    private List<SupplierDto> supplierDtoList;
    private List<SupplierDto> actualSupplierList;


    @BeforeEach
    public void setup() {
        log.info("SingleSupplierDbTest::setup");
        supplierDto = new SupplierData().createSupplierData();
        supplierDbService = new SupplierDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("SingleSupplierDbTest::deleteRecords");

        // delete a supplier
        supplierDbService.deleteSupplier(actualSupplierList);

        // validate if the supplier is not present in db after deleted
        actualSupplierList = supplierDbService.getSupplierBySupplierId(supplierDto.getSupplierId());
        Assertions.assertTrue(actualSupplierList.isEmpty());
    }

    @Test
    @Description("")
    public void testOneSupplier() {
        log.info("SingleSupplierDbTest::testOneSupplier");

        // insert a supplier
        supplierDtoList = supplierDbService.insertSupplier(supplierDto);

        // get the supplier from the db
        actualSupplierList = supplierDbService.getSupplierBySupplierId(supplierDto.getSupplierId());

        // validate
        Assertions.assertEquals(supplierDtoList.size(), actualSupplierList.size(), "expected and actual supplier size do not match");
        Assertions.assertFalse(actualSupplierList.isEmpty(), "inserted supplier mustn't be empty");
        Assertions.assertEquals(supplierDto.getSupplierId(), actualSupplierList.get(0).getSupplierId());
        Assertions.assertEquals(supplierDto.getSupplierName(), actualSupplierList.get(0).getSupplierName());
        Assertions.assertEquals(supplierDto.getContactName(), actualSupplierList.get(0).getContactName());
        Assertions.assertEquals(supplierDto.getAddress(), actualSupplierList.get(0).getAddress());
        Assertions.assertEquals(supplierDto.getCity(), actualSupplierList.get(0).getCity());
        Assertions.assertEquals(supplierDto.getPostalCode(), actualSupplierList.get(0).getPostalCode());
        Assertions.assertEquals(supplierDto.getCountry(), actualSupplierList.get(0).getCountry());
    }
}
