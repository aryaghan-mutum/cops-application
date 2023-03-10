package com.copsdbtest.programs;

import com.copsdbtest.constants.DbConstants;
import com.copsdbtest.dbservice.CustomerDbService;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dbservice.ProductDbService;
import com.copsdbtest.dbservice.SupplierDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

@Slf4j
public class DeleteDataInDb {

    public static void main(String[] args) {
//        deleteDataInTables(DbConstants.SUPPLIER_DB);
        deleteDataInAllTables();
    }

    @Description("delete rows to table based on table")
    private static void deleteDataInTables(String tableName) {
        if (tableName.equalsIgnoreCase(DbConstants.CUSTOMER_DB)) {
            new CustomerDbService().deleteAllCustomers();
        } else if (tableName.equalsIgnoreCase(DbConstants.ORDER_DB)) {
            new OrderDbService().deleteAllOrders();
        } else if (tableName.equalsIgnoreCase(DbConstants.PRODUCT_DB)) {
            new ProductDbService().deleteAllProducts();
        } else if (tableName.equalsIgnoreCase(DbConstants.SUPPLIER_DB)) {
            new SupplierDbService().deleteAllSuppliers();
        }
    }

    @Description("delete rows to table based on table")
    private static void deleteDataInAllTables() {
        new CustomerDbService().deleteAllCustomers();
        new OrderDbService().deleteAllOrders();
        new ProductDbService().deleteAllProducts();
        new SupplierDbService().deleteAllSuppliers();
    }
}
