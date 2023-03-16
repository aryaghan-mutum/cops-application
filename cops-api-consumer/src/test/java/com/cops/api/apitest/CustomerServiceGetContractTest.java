package com.cops.api.apitest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import com.cops.api.model.CustomerModel;

@PactDirectory("/tmp/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServiceGetContractTest {

    private static String CUSTOMER_ID = "c001";
    private static String CUSTOMER_NAME = "customer1";
    private static String CONTACT_NAME = "contact1";
    private static String ADDRESS = "main street";
    private static String CITY = "blr";
    private static String POSTAL_CODE = "560077";
    private static String COUNTRY = "IN";

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null,
            RandomPort.getInstance().getPort(), this);

    @Autowired
    private CustomerServiceClient customerServiceClient;


    @Pact(consumer = "customer_consumer")
    public RequestResponsePact pactForGetCustomer(PactDslWithProvider builder) {

        DslPart body = LambdaDsl.newJsonBody((o) -> o
                .stringType("customerId", CUSTOMER_ID)
                .stringType("customerName", CUSTOMER_NAME)
                .stringType("contactName", CONTACT_NAME)
                .stringType("address", ADDRESS)
                .stringType("city", CITY)
                .stringType("postalCode", POSTAL_CODE)
                .stringType("country", COUNTRY)
        ).build();

        return builder.given(
                "Customer GET: the customer ID matches an existing customer")
                .uponReceiving("A request for customer data")
                .path(String.format("/customer/%s", CUSTOMER_ID))
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @PactVerification(fragment = "pactForGetCustomer")
    @Test
    public void testFor_GET_existingCustomerId_shouldYieldExpectedCustomerData() {

        final CustomerModel customer = customerServiceClient.getCustomer(CUSTOMER_ID.toString());

        assertThat(customer.getCustomerId()).isEqualTo(CUSTOMER_ID);
        assertThat(customer.getCustomerName()).isEqualTo(CUSTOMER_NAME);
        assertThat(customer.getContactName()).isEqualTo(CONTACT_NAME);
        assertThat(customer.getAddress()).isEqualTo(ADDRESS);
        assertThat(customer.getCity()).isEqualTo(CITY);
        assertThat(customer.getPostalCode()).isEqualTo(POSTAL_CODE);
        assertThat(customer.getCountry()).isEqualTo(COUNTRY);
    }

}
