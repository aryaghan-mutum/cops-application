package com.copsperf.constants;

public class EndpointConstants {

    public final static String BASE_URL = "http://localhost:8080";

    // cops apis
    public final static String GET_ALL_CUSTOMERS_URL = BASE_URL + "/customer/getCustomersList";
    public final static String GET_CUSTOMER_BY_ID_URL = BASE_URL + "getCustomerById/%s";
    public final static String ADD_CUSTOMER_URL = BASE_URL + "/customer/addCustomer";
    public final static String DELETE_CUSTOMER_URL = BASE_URL + "/customer/deleteCustomer";
}
