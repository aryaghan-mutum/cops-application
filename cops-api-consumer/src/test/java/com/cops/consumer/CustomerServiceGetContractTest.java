package com.cops.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.cops.consumer.util.PortUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.cops.consumer.model.CustomerModel;

@PactDirectory("C:/dev/my-projects/app/cops-application/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServiceGetContractTest {

    private final static String CUSTOMER_ID = "c001";
    private final static String CUSTOMER_NAME = "customer1";
    private final static String CONTACT_NAME = "contact1";
    private final static String ADDRESS = "main street";
    private final static String CITY = "blr";
    private final static String POSTAL_CODE = "560077";
    private final static String COUNTRY = "IN";

    private final static String PATH = String.format("/customer/getCustomerById/%s", CUSTOMER_ID);

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null, PortUtil.getInstance().getPort(), this);

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

        RequestResponsePact requestResponsePact = builder.given(
                        "Customer GET: the customer ID matches an existing customer")
                .uponReceiving("A request for customer data")
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
    public void testFor_GET_existingCustomerId_shouldYieldExpectedCustomerData() {
        final CustomerModel customerResponse = customerServiceClient.getCustomer(CUSTOMER_ID);
        assertThat(customerResponse.getCustomerId()).isEqualTo(CUSTOMER_ID);
        assertThat(customerResponse.getCustomerName()).isEqualTo(CUSTOMER_NAME);
        assertThat(customerResponse.getContactName()).isEqualTo(CONTACT_NAME);
        assertThat(customerResponse.getAddress()).isEqualTo(ADDRESS);
        assertThat(customerResponse.getCity()).isEqualTo(CITY);
        assertThat(customerResponse.getPostalCode()).isEqualTo(POSTAL_CODE);
        assertThat(customerResponse.getCountry()).isEqualTo(COUNTRY);
    }

}
