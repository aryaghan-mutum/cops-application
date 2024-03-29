package com.cops.consumer.customer;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import com.cops.consumer.CustomerServiceClient;
import com.cops.consumer.model.CustomerModel;
import com.cops.consumer.util.PortUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@PactDirectory("C:/dev/my-projects/app/cops-application/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServiceGetCustomersListHappyPath {

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null, PortUtil.getInstance().getPort(), this);

    @Autowired
    private CustomerServiceClient customerServiceClient;

    private CustomerModel customerModel;

    public CustomerServiceGetCustomersListHappyPath() {
        customerModel = new CustomerModel();
        customerModel.setCustomerId("c001");
        customerModel.setCustomerName("customer1");
        customerModel.setContactName("contact1");
        customerModel.setAddress("main street");
        customerModel.setCity("blr");
        customerModel.setPostalCode("560077");
        customerModel.setCountry("IN");
    }

    @Pact(consumer = "customer_consumer_get_all_customers_happy_path")
    public RequestResponsePact pactForGetAllCustomers(PactDslWithProvider builder) {
        DslPart body = LambdaDsl.newJsonBody((o) -> o
                .stringType("customerId", customerModel.getCustomerId())
                .stringType("customerName", customerModel.getCustomerName())
                .stringType("contactName", customerModel.getContactName())
                .stringType("address", customerModel.getAddress())
                .stringType("city", customerModel.getCity())
                .stringType("postalCode", customerModel.getPostalCode())
                .stringType("country", customerModel.getCountry())
        ).build();

        RequestResponsePact requestResponsePact = builder
                .given("customer exist")
                .uponReceiving("get all customers")
                .path("/customer/getCustomersList")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .body(body)
                .toPact();
        return requestResponsePact;
    }

    @PactVerification(fragment = "pactForGetAllCustomers")
    @Test
    public void getCustomersListHappyPath() {
        log.info("CustomerServiceGetPositiveTest::getCustomersListHappyPath");
        ResponseEntity<CustomerModel> customerResponse = customerServiceClient.getCustomersList();
        log.info("Customer Response: ");
        log.info(String.valueOf(customerResponse.getBody()));
        assertThat(customerResponse.getBody().getCustomerId()).isEqualTo(customerModel.getCustomerId());
        assertThat(customerResponse.getBody().getCustomerName()).isEqualTo(customerModel.getCustomerName());
        assertThat(customerResponse.getBody().getContactName()).isEqualTo(customerModel.getContactName());
        assertThat(customerResponse.getBody().getAddress()).isEqualTo(customerModel.getAddress());
        assertThat(customerResponse.getBody().getCity()).isEqualTo(customerModel.getCity());
        assertThat(customerResponse.getBody().getPostalCode()).isEqualTo(customerModel.getPostalCode());
        assertThat(customerResponse.getBody().getCountry()).isEqualTo(customerModel.getCountry());
    }

}
