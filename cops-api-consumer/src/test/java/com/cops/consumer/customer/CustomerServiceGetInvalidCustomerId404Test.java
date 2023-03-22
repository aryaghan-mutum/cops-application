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
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@PactDirectory("C:/dev/my-projects/app/cops-application/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServiceGetInvalidCustomerId404Test {

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null, PortUtil.getInstance().getPort(), this);

    @Autowired
    private CustomerServiceClient customerServiceClient;

    private CustomerModel customerModel;

    public CustomerServiceGetInvalidCustomerId404Test() {
        customerModel = new CustomerModel();
        customerModel.setCustomerId("");
        customerModel.setCustomerName("customer1");
        customerModel.setContactName("contact1");
        customerModel.setAddress("main street");
        customerModel.setCity("blr");
        customerModel.setPostalCode("560077");
        customerModel.setCountry("IN");
    }

    @Pact(consumer = "customer_consumer_get_api_404_not_found")
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
                .given("Customer GET: Empty customerId")
                .uponReceiving("404 Not Found")
                .path(PATH)
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.NOT_FOUND.value())
                .body(body)
                .toPact();

        return requestResponsePact;
    }

    @Test
    @PactVerification(fragment = "pactForGetCustomer")
    public void getCustomerByIddWhenCustomerIdIsNotSet() {
        log.info("CustomerServiceGetInvalidCustomerId404Test::getCustomerByIddWhenCustomerIdIsNotSet");
        ResponseEntity<CustomerModel> customerResponse = customerServiceClient.getCustomerById(customerModel.getCustomerId());
        assertThat(customerResponse.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(customerResponse.getStatusCode().getReasonPhrase()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
        assertNull(customerResponse.getBody());
    }
}
