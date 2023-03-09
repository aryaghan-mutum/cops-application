package com.copsdbtest;

import com.copsdbtest.data.CustomerData;
import com.copsdbtest.dbservice.CustomerDbService;

import com.copsdbtest.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class CustomerDbTest {

    private CustomerDto customerDto;
    private CustomerDbService customerDbService;

    @BeforeEach
    public void setup() {
        log.info("CustomerDbTest::setup");
        customerDto = new CustomerData().createCustomerData();
        customerDbService = new CustomerDbService();
    }

    @Test
    public void testCustomer() {
        log.info("CustomerDbTest::testCustomer");
        customerDbService.insertMultipleCustomers(5);
    }

    @AfterEach
    public void deleteRecords() {
        log.info("CustomerDbTest::deleteRecords");
      //  customerDbService.deleteCustomer(customerDto);
    }

}
