package com.cops.controller;

import com.cops.db.OrderDB;
import com.cops.model.OrderModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderDB orderDB;

    @GetMapping("/getOrdersList")
    public List<OrderModel> getOrdersList() {
        log.info("OrderController::getOrdersList");
        return orderDB.findAll();
    }

    @PostMapping("/addOrder")
    public OrderModel addOrder(@RequestBody OrderModel orderModel) {
        log.info("OrderController::addOrder");
        return orderDB.save(orderModel);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrder(@RequestBody OrderModel orderModel) {
        log.info("OrderController::deleteOrder");
        orderDB.delete(orderModel);
    }

    @PutMapping("/updateOrder")
    public void updateOrder(@RequestBody OrderModel orderModel) {
        log.info("OrderController::updateOrder");
        orderDB.save(orderModel);
    }
}
