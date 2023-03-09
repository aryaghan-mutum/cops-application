package com.copsdbtest.dbservice;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.constants.QueryConstants;
import com.copsdbtest.data.OrderData;
import com.copsdbtest.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class OrderDbService {

    @Description("get total orders count")
    public long getAllOrdersCount() {
        log.info("OrderDb::getAllOrdersCount");
        return getAllOrders().stream().count();
    }

    @Description("get all the orders rows")
    public List<OrderDto> getAllOrders() {
        log.info("OrderDb::getAllOrders");
        List<OrderDto> orderDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.SELECT_ALL_ORDERS)) {;
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                int employeeId = resultSet.getInt("employee_id");
                String orderDate = resultSet.getString("order_date");
                int shipperId = resultSet.getInt("shipper_id");
                OrderDto orderDto = new OrderDto(order_id, customerId, employeeId, orderDate, shipperId);
                orderDtoList.add(orderDto);
            }

        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return orderDtoList;
    }

    @Description("get order by order_id")
    public List<OrderDto> getOrderByOrderId(int orderIdPrimaryKey) {
        log.info("OrderDb::getOrderByOrderId");
        List<OrderDto> orderDbList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from orders where order_id = %s", orderIdPrimaryKey))) {
            log.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");
                int employeeId = resultSet.getInt("employee_id");
                String orderDate = resultSet.getString("order_date");
                int shipperId = resultSet.getInt("shipper_id");
                OrderDto orderDto = new OrderDto(order_id, customerId, employeeId, orderDate, shipperId);
                orderDbList.add(orderDto);
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return orderDbList;
    }

    @Description("insert a order to db")
    public List<OrderDto> insertOrder(OrderDto orderDto) {
        log.info("OrderDb::insertOrder");
        log.info(orderDto.toString());
        List<OrderDto> orderDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.INSERT_ORDER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setInt(1, orderDto.getOrderId());
            preparedStatement.setInt(2, orderDto.getCustomerId());
            preparedStatement.setInt(3, orderDto.getEmployeeId());
            preparedStatement.setString(4, orderDto.getOrderDate());
            preparedStatement.setInt(5, orderDto.getShipperId());
            orderDtoList.add(orderDto);
            log.info(String.format("Total Rows Inserted: %s for order_id: %s", preparedStatement.executeUpdate(), orderDto.getOrderId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return orderDtoList;
    }

    @Description("insert multiple orders to db")
    public List<OrderDto> insertMultipleOrders(int ordersCount) {
        log.info("OrderDb::insertMultipleOrders");
        List<OrderDto> orderDbList = new ArrayList<>();
        IntStream.rangeClosed(1, ordersCount).forEach(order -> {
            OrderData orderData = new OrderData();
            OrderDto orderDto = orderData.createOrderData();
            insertOrder(orderDto);
            orderDbList.add(orderDto);
            log.info("----------------------------------");
        });
        return orderDbList;
    }

    @Description("delete an order in db")
    public void deleteOrder(OrderDto orderDto) {
        log.info("OrderDb::deleteOrder");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ORDER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setInt(1, orderDto.getOrderId());
            log.info(String.format("Total Rows Deleted: %s for order_id: %s", preparedStatement.executeUpdate(), orderDto.getOrderId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("delete an order in db")
    public void deleteOrder(List<OrderDto> orderDtoList) {
        log.info("OrderDb::deleteOrder");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ORDER)) {
            log.info(preparedStatement.toString());

            for (OrderDto orderDto : orderDtoList) {
                preparedStatement.setInt(1, orderDto.getOrderId());
                log.info(String.format("Total Rows Deleted: %s for order_id: %s", preparedStatement.executeUpdate(), orderDto.getOrderId()));
            }
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("check if a order is present in db")
    public boolean isOrderPresent(List<OrderDto> orderDtoList) {
        log.info("OrderDb::isOrderPresent");
        boolean isOrderIdPresent = false;
        for (OrderDto orderDto : orderDtoList) {
            try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(String.format("select * from orders where order_id = %s", orderDto.getOrderId()))) {
                log.info(preparedStatement.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                isOrderIdPresent = resultSet.next();
            } catch (SQLException e) {
                ConfigDbService.printSQLException(e);
            }
        }
        return isOrderIdPresent;
    }
}
