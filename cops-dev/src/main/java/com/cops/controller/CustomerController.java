package com.cops.controller;

import com.cops.db.CustomerDB;
import com.cops.model.CustomerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerDB customerDB;

    @GetMapping("/getCustomerById/{customerId}")
    public CustomerModel getCustomerById(@PathVariable String customerId) {
        log.info("CustomerController::getCustomerById" + customerId);
        return customerDB.findById(customerId).get();
    }

    @GetMapping("/getCustomersList")
    public List<CustomerModel> getCustomersList() {
        log.info("CustomerController::getCustomersList");
        return customerDB.findAll();
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::addCustomer");
        customerDB.save(customerModel);
    }

    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::deleteCustomer");
        customerDB.delete(customerModel);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::updateCustomer");
        customerDB.save(customerModel);
    }
}
