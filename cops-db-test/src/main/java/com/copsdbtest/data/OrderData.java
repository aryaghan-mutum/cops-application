package com.copsdbtest.data;

import com.copsdbtest.dto.CustomerDto;
import com.copsdbtest.dto.OrderDto;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.text.SimpleDateFormat;

@Slf4j
public class OrderData {

    private Faker faker = new Faker();

    @Description("generate order data")
    public OrderDto createOrderData() {
        log.info("OrderData::createOrderData");
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(faker.number().numberBetween(10000, 99999));
        orderDto.setCustomerId(faker.number().numberBetween(1, 999));
        orderDto.setEmployeeId(faker.number().numberBetween(1, 999));
        orderDto.setOrderDate(new SimpleDateFormat("MM-dd-yyyy").format(faker.date().birthday()));
        orderDto.setShipperId(faker.number().numberBetween(1, 99));
        return orderDto;
    }
}
