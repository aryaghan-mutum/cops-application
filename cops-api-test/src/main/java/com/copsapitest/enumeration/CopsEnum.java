package com.copsapitest.enumeration;

public enum CopsEnum {

    CUSTOMER("CUSTOMER_SERVICE", "/customer/getCustomersList", "/customer/addCustomer", "/customer/deleteCustomer", "/customer/updateCustomer"),
    ORDER("ORDER_SERVICE", "/order/getOrdersList", "/order/addOrder", "/order/deleteOrder", "/order/updateOrder"),
    PRODUCT("PRODUCT_SERVICE", "/product/getProductsList", "/product/addProduct", "/product/deleteProduct", "/product/updateProduct"),
    SUPPLIER("SUPPLIER_SERVICE", "/supplier/getSuppliersList", "/supplier/addSupplier", "/supplier/addSupplier", "/supplier/updateSupplier");

    private String serviceName;
    private String getServiceUrl;
    private String postServiceUrl;
    private String deleteServiceUrl;
    private String putServiceUrl;

    private CopsEnum(String serviceName, String getServiceUrl, String postServiceUrl, String deleteServiceUrl, String putServiceUrl) {
        this.serviceName = serviceName;
        this.getServiceUrl = getServiceUrl;
        this.postServiceUrl = postServiceUrl;
        this.deleteServiceUrl = deleteServiceUrl;
        this.putServiceUrl = putServiceUrl;
    }

    public String fetchServiceUrl() {
        return this.serviceName;
    }

    public String fetchGETServiceUrl() {
        return this.getServiceUrl;
    }

    public String fetchPOSTServiceUrl() {
        return this.postServiceUrl;
    }

    public String fetchDELETEServiceUrl() {
        return this.deleteServiceUrl;
    }

    public String fetchPUTServiceUrl() {
        return this.putServiceUrl;
    }

}
