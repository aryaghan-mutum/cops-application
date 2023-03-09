package com.copsdbtest.order;

import com.copsdbtest.data.OrderData;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;

@Slf4j
public class SingleOrderDbTest {
    private OrderDbService orderDbService;
    private OrderDto orderDto;

    private List<OrderDto> orderDtoList;
    private List<OrderDto> actualOrderList;


    @BeforeEach
    public void setup() {
        log.info("SingleOrderDbTest::setup");
        orderDto = new OrderData().createOrderData();
        orderDbService = new OrderDbService();
    }

    @AfterEach
    public void deleteRecords() {
        log.info("SingleOrderDbTest::deleteRecords");

        // delete a order
        orderDbService.deleteOrder(orderDtoList);

        // validate if the order is not present in db after deleted
        actualOrderList = orderDbService.getOrderByOrderId(orderDto.getOrderId());
        Assertions.assertTrue(actualOrderList.isEmpty());
    }

    @Test
    @Description("")
    public void testOneOrder() {
        log.info("SingleOrderDbTest::testOneOrder");

        // insert an order
        orderDtoList = orderDbService.insertOrder(orderDto);

        // get the order from the db
        actualOrderList = orderDbService.getOrderByOrderId(orderDto.getOrderId());

        // validate
        Assertions.assertEquals(orderDtoList.size(), actualOrderList.size(), "expected and actual order size do not match");
        Assertions.assertFalse(actualOrderList.isEmpty(), "inserted orders mustn't be empty");
        Assertions.assertEquals(orderDto.getOrderId(), actualOrderList.get(0).getOrderId());
        Assertions.assertEquals(orderDto.getCustomerId(), actualOrderList.get(0).getCustomerId());
        Assertions.assertEquals(orderDto.getEmployeeId(), actualOrderList.get(0).getEmployeeId());
        Assertions.assertEquals(orderDto.getOrderDate(), actualOrderList.get(0).getOrderDate());
        Assertions.assertEquals(orderDto.getShipperId(), actualOrderList.get(0).getShipperId());
    }
}
