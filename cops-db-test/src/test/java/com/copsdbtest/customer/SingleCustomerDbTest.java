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

import java.util.List;

@Slf4j
public class SingleCustomerDbTest {
    private CustomerDbService customerDbService;
    private CustomerDto customerDto;

    private List<CustomerDto> customerDtoList;
    private List<CustomerDto> actualCustomerList;


    @BeforeEach
    public void setup() {
        log.info("SingleCustomerDbTest::setup");
        customerDto = new CustomerData().createCustomerData();
        customerDbService = new CustomerDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("SingleCustomerDbTest::deleteRecords");

        // delete a customer
        customerDbService.deleteCustomer(customerDtoList);

        // validate if the customer is not present in db after deleted
        actualCustomerList = customerDbService.getCustomerByCustomerId(customerDto.getCustomerId());
        Assertions.assertTrue(actualCustomerList.isEmpty());
    }

    @Test
    @Description("")
    public void testOneCustomer() {
        log.info("SingleCustomerDbTest::testOneCustomer");

        // insert a customer
        customerDtoList = customerDbService.insertCustomer(customerDto);

        // get the customer from the db
        actualCustomerList = customerDbService.getCustomerByCustomerId(customerDto.getCustomerId());

        // validate
        Assertions.assertEquals(customerDtoList.size(), actualCustomerList.size(), "expected and actual customer size do not match");
        Assertions.assertFalse(actualCustomerList.isEmpty(), "inserted customers mustn't be empty");
        Assertions.assertEquals(customerDto.getCustomerId(), actualCustomerList.get(0).getCustomerId());
        Assertions.assertEquals(customerDto.getCustomerName(), actualCustomerList.get(0).getCustomerName());
        Assertions.assertEquals(customerDto.getContactName(), actualCustomerList.get(0).getContactName());
        Assertions.assertEquals(customerDto.getAddress(), actualCustomerList.get(0).getAddress());
        Assertions.assertEquals(customerDto.getCity(), actualCustomerList.get(0).getCity());
        Assertions.assertEquals(customerDto.getPostalCode(), actualCustomerList.get(0).getPostalCode());
        Assertions.assertEquals(customerDto.getCountry(), actualCustomerList.get(0).getCountry());
    }
}
