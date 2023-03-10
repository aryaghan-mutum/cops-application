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

import java.util.List;

@Slf4j
public class InsertDataToDb {

    private static final int TOTAL_ROWS_TO_BE_INSERTED = 10;
    public static final String TABLE_NAME = DbConstants.SUPPLIER_DB;

    public static void main(String[] args) {
      //  addDataToTable(TABLE_NAME);
        addDataToTables();
    }

    @Description("add rows to table based on table")
    private static void addDataToTable(String tableName) {
        if (tableName.equalsIgnoreCase(DbConstants.CUSTOMER_DB)) {
            new CustomerDbService().insertMultipleCustomers(TOTAL_ROWS_TO_BE_INSERTED);
        } else if (tableName.equalsIgnoreCase(DbConstants.ORDER_DB)) {
            new OrderDbService().insertMultipleOrders(TOTAL_ROWS_TO_BE_INSERTED);
        } else if (tableName.equalsIgnoreCase(DbConstants.PRODUCT_DB)) {
            new ProductDbService().insertMultipleProducts(TOTAL_ROWS_TO_BE_INSERTED);
        } else if (tableName.equalsIgnoreCase(DbConstants.SUPPLIER_DB)) {
            new SupplierDbService().insertMultipleSuppliers(TOTAL_ROWS_TO_BE_INSERTED);
        }
    }

    @Description("add rows to all the tables")
    private static void addDataToTables() {
        new CustomerDbService().insertMultipleCustomers(TOTAL_ROWS_TO_BE_INSERTED);
        new OrderDbService().insertMultipleOrders(TOTAL_ROWS_TO_BE_INSERTED);
        new ProductDbService().insertMultipleProducts(TOTAL_ROWS_TO_BE_INSERTED);
        new SupplierDbService().insertMultipleSuppliers(TOTAL_ROWS_TO_BE_INSERTED);
    }
}
