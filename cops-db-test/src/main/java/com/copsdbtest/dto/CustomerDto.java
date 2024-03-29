package com.copsdbtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private String customerId;
    private String customerName;
    private String contactName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
