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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class MultipleSupplierDbTest {
    private SupplierDbService supplierDbService;
    private List<SupplierDto> supplierDtoList;
    private List<SupplierDto> actualSupplierList;

    @BeforeEach
    public void setup() {
        log.info("MultipleSupplierDbTest::setup");
        supplierDbService = new SupplierDbService();
        supplierDtoList = new ArrayList<>();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("MultipleSupplierDbTest::deleteRecords");

        // delete a supplier
        supplierDbService.deleteSupplier(supplierDtoList);
    }

    @Test
    @Description("test ten suppliers")
    public void testTenSuppliers() {
        log.info("MultipleSupplierDbTest::testTenSuppliers");

        // insert multiple suppliers
        SupplierData supplierData = new SupplierData();
        final int suppliersCount = 10;

        IntStream.rangeClosed(1, suppliersCount)
                .mapToObj(supplier -> supplierData.createSupplierData())
                .forEach(supplierDto -> {
                    supplierDtoList.add(supplierDbService.insertSupplier(supplierDto).get(0));

                    // get the supplier from the db and validate
                    actualSupplierList = supplierDbService.getSupplierBySupplierId(supplierDto.getSupplierId());

                    // validate
                    Assertions.assertFalse(actualSupplierList.isEmpty(), "inserted suppliers mustn't be empty");
                    Assertions.assertEquals(supplierDto.getSupplierId(), actualSupplierList.get(0).getSupplierId());
                    Assertions.assertEquals(supplierDto.getSupplierName(), actualSupplierList.get(0).getSupplierName());
                    Assertions.assertEquals(supplierDto.getContactName(), actualSupplierList.get(0).getContactName());
                    Assertions.assertEquals(supplierDto.getAddress(), actualSupplierList.get(0).getAddress());
                    Assertions.assertEquals(supplierDto.getCity(), actualSupplierList.get(0).getCity());
                    Assertions.assertEquals(supplierDto.getPostalCode(), actualSupplierList.get(0).getPostalCode());
                    Assertions.assertEquals(supplierDto.getCountry(), actualSupplierList.get(0).getCountry());
                });
    }
}
