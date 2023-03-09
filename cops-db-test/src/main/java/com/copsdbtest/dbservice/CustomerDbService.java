package com.copsdbtest.dbservice;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.constants.QueryConstants;
import com.copsdbtest.data.CustomerData;
import com.copsdbtest.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class CustomerDbService {

    @Description("get total customers count")
    public long getCustomersCount() {
        log.info("CustomerDb::getCustomersCount");
        return getAllCustomers().stream().count();
    }

    @Description("get all the customer rows")
    public List<CustomerDto> getAllCustomers() {
        log.info("CustomerDb::getAllCustomers");
        List<CustomerDto> customerDtoList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.SELECT_ALL_CUSTOMERS)) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerId = resultSet.getString("customer_id");
                String customerName = resultSet.getString("customer_name");
                String contactName = resultSet.getString("contact_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String postal_code = resultSet.getString("postal_code");
                String country = resultSet.getString("country");
                CustomerDto customerDto = new CustomerDto(customerId, customerName, contactName, address, city, postal_code, country);
                customerDtoList.add(customerDto);
            }

        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
        return customerDtoList;
    }

    @Description("insert a customer to db")
    public void insertCustomer(CustomerDto customerDto) {
        log.info("CustomerDb::insertCustomer");
        log.info(customerDto.toString());
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.INSERT_CUSTOMER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setString(1, customerDto.getCustomerId());
            preparedStatement.setString(2, customerDto.getCustomerName());
            preparedStatement.setString(3, customerDto.getContactName());
            preparedStatement.setString(4, customerDto.getAddress());
            preparedStatement.setString(5, customerDto.getCity());
            preparedStatement.setString(6, customerDto.getPostalCode());
            preparedStatement.setString(7, customerDto.getCountry());
            log.info(String.format("Total Rows Inserted: %s for customer_id: %s", preparedStatement.executeUpdate(), customerDto.getCustomerId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }

    @Description("insert multiple customers to db")
    public void insertMultipleCustomers(int customersCount) {
        log.info("CustomerDb::insertMultipleCustomers");
        IntStream.rangeClosed(1, customersCount).forEach(customer -> {
            CustomerData customerData = new CustomerData();
            CustomerDto customerDto = customerData.createCustomerData();
            insertCustomer(customerDto);
        });
    }

    @Description("delete a customer in db")
    public void deleteCustomer(CustomerDto customerDto) {
        log.info("CustomerDb::deleteCustomer");
        try (Connection connection = DriverManager.getConnection(DbConstants.DB_URL, DbConstants.DB_USER, DbConstants.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_CUSTOMER)) {
            log.info(preparedStatement.toString());
            preparedStatement.setString(1, customerDto.getCustomerId());
            log.info(String.format("Total Rows Deleted: %s for product_id: %s", preparedStatement.executeUpdate(), customerDto.getCustomerId()));
        } catch (SQLException e) {
            ConfigDbService.printSQLException(e);
        }
    }
}
