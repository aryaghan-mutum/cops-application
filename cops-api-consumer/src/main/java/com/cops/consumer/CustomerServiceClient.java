package com.cops.consumer;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.cops.consumer.model.CustomerModel;

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

    public CustomerModel getCustomer(String customerId) {
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

    public CustomerModel getCustomersList() {
        return restTemplate.getForObject("/customer/getCustomersList", CustomerModel.class);
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