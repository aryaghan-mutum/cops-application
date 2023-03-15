package com.cops.contract;

import com.cops.controller.CustomerController;
import com.cops.model.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class H {

    private CustomerController customerController;

    private CustomerModel customerModel = new CustomerModel();

    @Test
    public void f() {

        customerModel.setCustomerId("12345");
        customerModel.setCustomerName("Anurag");
        customerModel.setContactName("Anu");
        customerModel.setAddress("123 St, 33025");
        customerModel.setCity("Miramar");
        customerModel.setPostalCode("33025");
        customerModel.setCountry("USA");

        customerController = new CustomerController();
        customerController.getCustomersList();
        System.out.println();
    }
}
