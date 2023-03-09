package com.copsdbtest.data;

import com.copsdbtest.dto.CustomerDto;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.util.Locale;

@Slf4j
public class CustomerData {

    private Faker faker = new Faker();

    @Description("generate customer data")
    public CustomerDto createCustomerData() {
        log.info("CustomerData::createCustomerData");
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(faker.regexify("[a-z1-9]{10}").toUpperCase(Locale.ROOT));
        customerDto.setCustomerName(faker.name().fullName());
        customerDto.setContactName(faker.name().firstName());
        customerDto.setAddress(faker.address().fullAddress());
        customerDto.setCity(faker.address().city());
        customerDto.setPostalCode(faker.address().zipCode());
        customerDto.setCountry(faker.country().name());
        return customerDto;
    }
}
