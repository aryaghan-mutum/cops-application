package com.copsdbtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int orderId;
    private int customerId;
    private int employeeId;
    private String orderDate;
    private int shipperId;
}
