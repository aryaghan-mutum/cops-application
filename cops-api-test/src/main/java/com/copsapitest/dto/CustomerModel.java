package com.copsapitest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerModel {

    @JsonProperty("customer_list")
    private List<Customer> customerList = null;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Customer {
        @JsonProperty("customer_id")
        private int customerId;
        @JsonProperty("customer_name")
        private String customerName;
        @JsonProperty("contact_name")
        private String contactName;
        @JsonProperty("address")
        private String address;
        @JsonProperty("city")
        private String city;
        @JsonProperty("postalCode")
        private String postalCode;
        @JsonProperty("country")
        private String country;
    }
}
