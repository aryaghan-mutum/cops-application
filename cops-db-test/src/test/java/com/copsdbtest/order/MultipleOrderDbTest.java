package com.copsdbtest.order;

import com.copsdbtest.data.OrderData;
import com.copsdbtest.data.ProductData;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dbservice.ProductDbService;
import com.copsdbtest.dto.OrderDto;
import com.copsdbtest.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class MultipleOrderDbTest {
    private OrderDbService orderDbService;
    private List<OrderDto> orderDtoList;
    private List<OrderDto> actualOrderList;

    @BeforeEach
    public void setup() {
        log.info("MultipleOrderDbTest::setup");
        orderDbService = new OrderDbService();
        orderDtoList = new ArrayList<>();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("MultipleOrderDbTest::deleteRecords");

        // delete an order
        orderDbService.deleteOrder(orderDtoList);
    }

    @Test
    @Description("test ten orders")
    public void testTenOrders() {
        log.info("MultipleOrderDbTest::testTenOrders");

        // insert multiple orders
        OrderData orderData = new OrderData();
        final int ordersCount = 10;

        IntStream.rangeClosed(1, ordersCount)
                .mapToObj(order -> orderData.createOrderData())
                .forEach(orderDto -> {
                    orderDtoList.add(orderDbService.insertOrder(orderDto).get(0));

                    // get the product from the db and validate
                    actualOrderList = orderDbService.getOrderByOrderId(orderDto.getOrderId());

                    // validate
                    Assertions.assertFalse(actualOrderList.isEmpty(), "inserted products mustn't be empty");
                    Assertions.assertEquals(orderDto.getOrderId(), actualOrderList.get(0).getOrderId());
                    Assertions.assertEquals(orderDto.getCustomerId(), actualOrderList.get(0).getCustomerId());
                    Assertions.assertEquals(orderDto.getEmployeeId(), actualOrderList.get(0).getEmployeeId());
                    Assertions.assertEquals(orderDto.getOrderDate(), actualOrderList.get(0).getOrderDate());
                    Assertions.assertEquals(orderDto.getShipperId(), actualOrderList.get(0).getShipperId());
                });
    }
}
