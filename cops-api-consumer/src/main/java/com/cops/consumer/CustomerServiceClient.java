package com.cops.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.cops.consumer.model.CustomerModel;

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
        return restTemplate.getForObject(String.format("/customer/getCustomerById/%s", customerId), CustomerModel.class);
    }

}