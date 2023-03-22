package com.cops.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.cops.consumer.model.CustomerModel;

import java.io.IOException;

import static java.util.Objects.isNull;
import static org.springframework.http.ResponseEntity.*;

@Slf4j
@Component
public class CustomerServiceClient {

    private final RestTemplate restTemplate;

    public CustomerServiceClient(@Value("${customer_provider.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder()
                .rootUri(baseUrl)
                .defaultHeader("Connection", "close")
                .build();
    }

    public CustomerModel getCustomer2(String customerId) {
        CustomerModel customerModel = new CustomerModel();
        try {
            customerModel = restTemplate.getForObject(String.format("/customer/getCustomerById/%s", customerId), CustomerModel.class);
        } catch (HttpServerErrorException e) {
            log.info(String.valueOf(e));
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return customerModel;
    }

    public ResponseEntity<CustomerModel> getCustomerById(String customerId) {
        try {
            CustomerModel customer = this.restTemplate.getForObject(String.format("/customer/getCustomerById/%s", customerId), CustomerModel.class);
//            if (isNull(customer)) {
//                return notFound().build();
//            } else if (isNull(customer.getCustomerId())) {
//                return status(HttpStatus.NOT_FOUND).build();
//            }
            return ok(customer);
        } catch (Exception e) {
            return notFound().build();
        }
    }

    public ResponseEntity<CustomerModel> getCustomersList() {
        CustomerModel customer = this.restTemplate.getForObject("/customer/getCustomersList", CustomerModel.class);
        return ok(customer);
    }

    public CustomerModel addCustomer() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        CustomerModel customerModel = restTemplate.exchange(
                "/customer/addCustomer",
                HttpMethod.POST,
                new HttpEntity<>(null, httpHeaders),
                CustomerModel.class).getBody();
        return customerModel;
    }
}