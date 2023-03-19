package com.cops.consumer.customer;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.cops.consumer.CustomerServiceClient;
import com.cops.consumer.util.PortUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.cops.consumer.model.CustomerModel;

@Slf4j
@PactDirectory("C:/dev/my-projects/app/cops-application/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServiceGetCustomerByIdHappyPath {

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null, PortUtil.getInstance().getPort(), this);

    @Autowired
    private CustomerServiceClient customerServiceClient;

    private CustomerModel customerModel;

    public CustomerServiceGetCustomerByIdHappyPath() {
        customerModel = new CustomerModel();
        customerModel.setCustomerId("c001");
        customerModel.setCustomerName("customer1");
        customerModel.setContactName("contact1");
        customerModel.setAddress("main street");
        customerModel.setCity("blr");
        customerModel.setPostalCode("560077");
        customerModel.setCountry("IN");
    }

    @Pact(consumer = "customer_consumer_get_a_customer_happy_path")
    public RequestResponsePact pactForGetCustomer(PactDslWithProvider builder) {
        final String PATH = String.format("/customer/getCustomerById/%s", customerModel.getCustomerId());

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
                .given("Customer GET: the customer ID matches an existing customer")
                .uponReceiving("A request for customer data 200 status code")
                .path(PATH)
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .body(body)
                .toPact();

        return requestResponsePact;
    }

    @PactVerification(fragment = "pactForGetCustomer")
    @Test
    public void testFor_GET_existing_GetCustomerById() {
        log.info("CustomerServiceGetPositiveTest::testFor_GET_existingGetCustomerById");
        final CustomerModel customerResponse = customerServiceClient.getCustomer(customerModel.getCustomerId());
        log.info("Customer Response: ");
        log.info(String.valueOf(customerResponse));
        assertThat(customerResponse.getCustomerId()).isEqualTo(customerModel.getCustomerId());
        assertThat(customerResponse.getCustomerName()).isEqualTo(customerModel.getCustomerName());
        assertThat(customerResponse.getContactName()).isEqualTo(customerModel.getContactName());
        assertThat(customerResponse.getAddress()).isEqualTo(customerModel.getAddress());
        assertThat(customerResponse.getCity()).isEqualTo(customerModel.getCity());
        assertThat(customerResponse.getPostalCode()).isEqualTo(customerModel.getPostalCode());
        assertThat(customerResponse.getCountry()).isEqualTo(customerModel.getCountry());
    }
}
