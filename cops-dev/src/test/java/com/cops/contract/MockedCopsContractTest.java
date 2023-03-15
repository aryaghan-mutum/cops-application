//package com.cops.contract;
//
//import au.com.dius.pact.provider.junit.Provider;
//import au.com.dius.pact.provider.junit.State;
//import au.com.dius.pact.provider.junit.loader.PactFolder;
//import au.com.dius.pact.provider.junit.target.Target;
//import au.com.dius.pact.provider.junit.target.TestTarget;
//import au.com.dius.pact.provider.spring.SpringRestPactRunner;
//import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
//import com.amazonaws.services.kms.model.NotFoundException;
//import com.cops.controller.CustomerController;
//import com.cops.db.CustomerDB;
//import com.cops.model.CustomerModel;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRestPactRunner.class)
//@Provider("customer-service")
//@PactFolder("pacts")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class MockedCopsContractTest {
//
//    @TestTarget
//    public final Target target = new SpringBootHttpTarget();
//
//    @MockBean
//    private CustomerController customerController;
//
//    @MockBean
//    private CustomerDB customerDB;
//
//    CustomerModel customerModel = new CustomerModel();
//
//    @State("Customer 1 exists")
//    public void customer1Exists() {
//
//        customerModel.setCustomerId("12345");
//        customerModel.setCustomerName("Anurag");
//        customerModel.setContactName("Anu");
//        customerModel.setAddress("123 St, 33025");
//        customerModel.setCity("Miramar");
//        customerModel.setPostalCode("33025");
//        customerModel.setCountry("USA");
//
//        when(customerDB.findAll())
//                .thenReturn((List<CustomerModel>) customerModel);
//    }
//
//    @State("Customer 2 does not exist")
//    public void customer2DoesNotExist() {
//        when(customerController.getCustomersList()).thenThrow(NotFoundException.class);
//    }
//
//}
//
