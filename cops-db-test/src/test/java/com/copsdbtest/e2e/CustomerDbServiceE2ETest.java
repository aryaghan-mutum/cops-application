package com.copsdbtest.e2e;

import com.copsdbtest.data.CustomerData;
import com.copsdbtest.dbservice.CustomerDbService;
import com.copsdbtest.dbservice.OrderDbService;
import com.copsdbtest.dbservice.ProductDbService;
import com.copsdbtest.dbservice.SupplierDbService;
import com.copsdbtest.dto.CustomerDto;
import com.copsdbtest.dto.OrderDto;
import com.copsdbtest.dto.ProductDto;
import com.copsdbtest.dto.SupplierDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;

@Slf4j
public class CustomerDbServiceE2ETest {

    private CustomerDbService customerDbService;

    private OrderDbService orderDb;

    private ProductDbService productDbService;
    private SupplierDbService supplierDbService;

    @BeforeEach
    public void setup() {
        customerDbService = new CustomerDbService();
        orderDb = new OrderDbService();
        productDbService = new ProductDbService();
        supplierDbService = new SupplierDbService();
    }

    @Test
    @Description("test customer db")
    public void testCustomerDb() {
        log.info("CopsDbTest::testCustomerDb");
        List<CustomerDto> customerDtoList =  customerDbService.getAllCustomers();
        List<OrderDto> orderDtoList =  orderDb.getAllOrders();
        List<ProductDto> productDtoList =  productDbService.getAllProducts();
        List<SupplierDto> supplierDtoLists =  supplierDbService.getAllSuppliers();
    }

    @Test
    public void add() {
        //  config.insertCustomer2(new CustomerData().addCustomer());
        CustomerDto customerDto = new CustomerData().createCustomerData();
        customerDbService.insertCustomer(customerDto);
    }
}
