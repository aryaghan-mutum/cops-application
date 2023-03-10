package com.copsdbtest.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryConstants {

    // GET
    public final String SELECT_ALL_CUSTOMERS = "select * from customers";
    public final String SELECT_ALL_ORDERS = "select * from orders";
    public final String SELECT_ALL_PRODUCTS = "select * from products";
    public final String SELECT_ALL_SUPPLIERS = "select * from suppliers";

    // INSERT
    public final String INSERT_CUSTOMER = "INSERT INTO customers (customer_id, customer_name, contact_name, address, city, postal_code, country) VALUES (?,?,?,?,?,?,?)";
    public final String INSERT_ORDER = "INSERT INTO orders (order_id, customer_id, employee_id, order_date, shipper_id) VALUES (?,?,?,?,?)";
    public final String INSERT_PRODUCT = "INSERT INTO products (product_id, product_name, supplier_id, category_id, unit, price) VALUES (?,?,?,?,?,?)";
    public final String INSERT_SUPPLIER = "INSERT INTO suppliers (supplier_id, supplier_name, contact_name, address, city, postal_code, country) VALUES (?,?,?,?,?,?,?)";

    // DELETE BY PRIMARY KEY
    public final String DELETE_CUSTOMER = "delete from customers where customer_id=?";
    public final String DELETE_ORDER = "delete from orders where order_id=?";
    public final String DELETE_PRODUCT = "delete from products where product_id=?";
    public final String DELETE_SUPPLIER = "delete from suppliers where supplier_id=?";

    // DELETE ALL
    public final String DELETE_ALL_CUSTOMERS = "delete from customers";
    public final String DELETE_ALL_ORDERS = "delete from orders";
    public final String DELETE_ALL_PRODUCTS = "delete from products";
    public final String DELETE_ALL_SUPPLIERS = "delete from suppliers";
}
