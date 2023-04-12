package com.cops.controller;

import com.cops.db.CustomerDB;
import com.cops.model.CustomerModel;
import com.cops.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.notFound;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerDB customerDB;

    @GetMapping(value = "/getCustomerById/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable String customerId) {
        log.info("CustomerController::getCustomerById:" + customerId);
        try {
            CustomerModel customer = customerDB.findById(customerId).get();
            return ok().headers(ServiceUtil.getHeaders()).body(customer);
        } catch (NoSuchElementException e) {
            return notFound().build();
        }
    }

    @GetMapping(value = "/getCustomersList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerModel>> getCustomersList() {
        log.info("CustomerController::getCustomersList");
        List<CustomerModel> customersList = customerDB.findAll();
        return ResponseEntity.ok().headers(ServiceUtil.getHeaders()).body(customersList);
    }

    @PostMapping(value = "/addCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerModel addCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::addCustomer");
        CustomerModel saveCustomer = customerDB.save(customerModel);
        log.info("Customer Added: " + customerModel);
        return ResponseEntity.ok().headers(ServiceUtil.getHeaders()).body(saveCustomer).getBody();
    }

    @DeleteMapping(value = "/deleteCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::deleteCustomer");
        ResponseEntity.ok().headers(ServiceUtil.getHeaders()).build();
        customerDB.delete(customerModel);
        log.info("Customer Deleted: " + customerModel);
    }

    @PutMapping(value = "/updateCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerModel updateCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::updateCustomer");
        CustomerModel updateCustomer = customerDB.save(customerModel);
        log.info("Customer Updated: " + customerModel);
        return ResponseEntity.ok().headers(ServiceUtil.getHeaders()).body(updateCustomer).getBody();
    }
}
