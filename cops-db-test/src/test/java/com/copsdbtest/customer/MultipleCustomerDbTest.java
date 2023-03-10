package com.copsdbtest.customer;

import com.copsdbtest.data.CustomerData;
import com.copsdbtest.dbservice.CustomerDbService;
import com.copsdbtest.dto.CustomerDto;
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
public class MultipleCustomerDbTest {
    private CustomerDbService customerDbService;
    private List<CustomerDto> customerDtoList;
    private List<CustomerDto> actualCustomerList;

    @BeforeEach
    public void setup() {
        log.info("MultipleCustomerDbTest::setup");
        customerDbService = new CustomerDbService();
        customerDtoList = new ArrayList<>();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("MultipleCustomerDbTest::deleteRecords");

        // delete a customer
        customerDbService.deleteCustomer(customerDtoList);
    }

    @Test
    @Description("test ten customers")
    public void testTenCustomers() {
        log.info("MultipleCustomerDbTest::testTenCustomers");

        // insert multiple customers
        CustomerData customerData = new CustomerData();
        final int customersCount = 10;

        IntStream.rangeClosed(1, customersCount)
                .forEach(order -> {
                    customerDtoList.add(customerDbService.insertCustomer(customerData.createCustomerData()).get(0));

                    // get the product from the db and validate
                    actualCustomerList = customerDbService.getCustomerByCustomerId(customerData.createCustomerData().getCustomerId());

                    // validate
                    Assertions.assertFalse(actualCustomerList.isEmpty(), "inserted products mustn't be empty");
                    Assertions.assertEquals(customerData.createCustomerData().getCustomerId(), actualCustomerList.get(0).getCustomerId());
                    Assertions.assertEquals(customerData.createCustomerData().getCustomerName(), actualCustomerList.get(0).getCustomerName());
                    Assertions.assertEquals(customerData.createCustomerData().getContactName(), actualCustomerList.get(0).getContactName());
                    Assertions.assertEquals(customerData.createCustomerData().getAddress(), actualCustomerList.get(0).getAddress());
                    Assertions.assertEquals(customerData.createCustomerData().getCity(), actualCustomerList.get(0).getCity());
                    Assertions.assertEquals(customerData.createCustomerData().getPostalCode(), actualCustomerList.get(0).getPostalCode());
                    Assertions.assertEquals(customerData.createCustomerData().getCountry(), actualCustomerList.get(0).getCountry());
                });
    }
}
