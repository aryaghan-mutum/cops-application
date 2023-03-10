package com.copsdbtest.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DbConstants {
    public final String DB_URL = "jdbc:postgresql://localhost:5432/human_resources";
    public final String DB_USER = "postgres";
    public final String DB_PASSWORD = "Boffin@123";

    public final String CUSTOMER_DB = "CUSTOMER_DB";
    public final String ORDER_DB = "ORDER_DB";
    public final String PRODUCT_DB = "PRODUCT_DB";
    public final String SUPPLIER_DB = "SUPPLIER_DB";
}
