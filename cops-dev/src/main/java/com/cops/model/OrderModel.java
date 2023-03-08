package com.cops.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @Column(name="order_id")
    private int orderId;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="employee_id")
    private int employeeId;

    @Column(name="order_date")
    private String orderDate;

    @Column(name="shipper_id")
    private int shipperId;
}
