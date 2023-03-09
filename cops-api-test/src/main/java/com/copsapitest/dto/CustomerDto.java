package com.copsapitest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customerId",
        "customerName",
        "contactName",
        "address",
        "city",
        "postalCode",
        "country"
})
public class CustomerDto {

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
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("country")
    private String country;
}
