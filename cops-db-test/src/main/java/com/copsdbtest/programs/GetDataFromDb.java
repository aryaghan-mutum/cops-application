package com.copsdbtest.programs;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.dbservice.CustomerDbService;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dbservice.ProductDbService;
import com.copsdbtest.dbservice.SupplierDbService;
import com.copsdbtest.dto.CustomerDto;
import com.copsdbtest.dto.OrderDto;
import com.copsdbtest.dto.ProductDto;
import com.copsdbtest.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetDataFromDb {
    public static final String TABLE_NAME = DbConstants.SUPPLIER_DB;

    public static void main(String[] args) {
      //  getDataFromTable(TABLE_NAME);
        getDataFromAllTables();
    }

    @Description("get rows from a table based on table")
    private static void getDataFromTable(String tableName) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<ProductDto> productDtoList = new ArrayList<>();
        List<SupplierDto> supplierDtoList = new ArrayList<>();

        if (tableName.equalsIgnoreCase(DbConstants.CUSTOMER_DB)) {
            customerDtoList = new CustomerDbService().getAllCustomers();
        } else if (tableName.equalsIgnoreCase(DbConstants.ORDER_DB)) {
            orderDtoList = new OrderDbService().getAllOrders();
        } else if (tableName.equalsIgnoreCase(DbConstants.PRODUCT_DB)) {
            productDtoList = new ProductDbService().getAllProducts();
        } else if (tableName.equalsIgnoreCase(DbConstants.SUPPLIER_DB)) {
            supplierDtoList = new SupplierDbService().getAllSuppliers();
        }
        System.out.println();
    }

    @Description("get rows from all the tables")
    private static void getDataFromAllTables() {
        List<CustomerDto> customerDtoList = new CustomerDbService().getAllCustomers();
        List<OrderDto> orderDtoList = new OrderDbService().getAllOrders();
        List<ProductDto> productDtoList = new ProductDbService().getAllProducts();
        List<SupplierDto> supplierDtoList = new SupplierDbService().getAllSuppliers();
        System.out.println();
    }
}
