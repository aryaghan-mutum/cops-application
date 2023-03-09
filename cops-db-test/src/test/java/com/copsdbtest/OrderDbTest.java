package com.copsdbtest;

import com.copsdbtest.data.OrderData;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class OrderDbTest {

    private OrderDto orderDto;
    private OrderDbService orderDbService;

    @BeforeEach
    public void setup() {
        log.info("OrderDbTest::setup");
        orderDto = new OrderData().createOrderData();
        orderDbService = new OrderDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("OrderDbTest::deleteRecords");
        orderDbService.deleteOrder(orderDto);
        orderDbService.insertMultipleOrders(5);
    }

    @Test
    public void testOrders() {
        log.info("OrderDbTest::testOrders");
        orderDbService.insertOrder(orderDto);
    }
}
