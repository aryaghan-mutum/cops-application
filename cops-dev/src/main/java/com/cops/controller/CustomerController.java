package com.cops.controller;

import com.cops.db.CustomerDB;
import com.cops.model.CustomerModel;
import com.cops.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;
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
        return ResponseEntity.ok().headers(ServiceUtil.getHeaders()).body(saveCustomer).getBody();
    }

    @DeleteMapping(value = "/deleteCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::deleteCustomer");
        customerDB.delete(customerModel);
    }

    @PutMapping(value = "/updateCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerModel updateCustomer(@RequestBody CustomerModel customerModel) {
        log.info("CustomerController::updateCustomer");
        CustomerModel updateCustomer = customerDB.save(customerModel);
        return ResponseEntity.ok().headers(ServiceUtil.getHeaders()).body(updateCustomer).getBody();
    }
}
