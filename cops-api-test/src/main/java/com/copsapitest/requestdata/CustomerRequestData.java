package com.copsapitest.requestdata;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomerRequestData {

    private Faker faker = new Faker();

    @Description("add customer request body data")
    public Map<String, Object> addCustomer() {
        log.info("CustomerRequestData::addCustomer");
        int customerId = faker.number().numberBetween(1, 100);
        log.info("Customer ID Generated: " + customerId);

        Map<String, Object> requestBody = new HashMap();
        requestBody.put("customerId", faker.number().numberBetween(1, 100));
        requestBody.put("customerName", String.format("Automation %s", faker.name().fullName()));
        requestBody.put("contactName", faker.name().firstName());
        requestBody.put("address", faker.address().fullAddress());
        requestBody.put("city", faker.code().asin());
        requestBody.put("postalCode", String.valueOf(faker.number().randomNumber(5, true)));
        requestBody.put("country", faker.country().name());

        log.info("Request Created: ");
        log.info(new Gson().toJson(requestBody));

        return requestBody;
    }
}
