package com.copsperf.data;

import com.copsperf.pojo.request.AddCustomerRequestPojo;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TowelsRequestData {


    public static AddCustomerRequestPojo setAddCustomerRequestData() {
        Faker faker = new Faker();
        AddCustomerRequestPojo addCustomerRequestPojo = new AddCustomerRequestPojo();
        addCustomerRequestPojo.setCustomerId(faker.regexify("[a-z1-9]{10}").toUpperCase(Locale.ROOT));
        addCustomerRequestPojo.setCustomerName(faker.name().fullName());
        addCustomerRequestPojo.setContactName(faker.name().firstName());
        addCustomerRequestPojo.setAddress(faker.address().fullAddress());
        addCustomerRequestPojo.setCity(faker.address().city());
        addCustomerRequestPojo.setPostalCode(faker.address().zipCode());
        addCustomerRequestPojo.setCountry(faker.country().name());
        return addCustomerRequestPojo;
    }
}
