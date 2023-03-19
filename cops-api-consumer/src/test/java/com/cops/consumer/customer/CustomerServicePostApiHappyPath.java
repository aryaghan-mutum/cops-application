package com.cops.consumer.customer;

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
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@PactDirectory("C:/dev/my-projects/app/cops-application/pacts")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "customer_provider.base-url:http://localhost:${RANDOM_PORT}",
        classes = CustomerServiceClient.class)
public class CustomerServicePostApiHappyPath {

    @Rule
    public PactProviderRule provider = new PactProviderRule("customer_provider", null, PortUtil.getInstance().getPort(), this);

    @Autowired
    private CustomerServiceClient customerServiceClient;

    private CustomerModel customerModel;

    @Pact(consumer = "customer_consumer_post_api_happy_path")
    public RequestResponsePact pactForPostCustomer(PactDslWithProvider builder) {
        RequestResponsePact requestResponsePact = builder
                .given("Customer POST: Add a customer to DB")
                .uponReceiving("A request for adding customer data")
                .path("/customer/addCustomer")
                .method(HttpMethod.POST.name())
                .body(" {\n" +
                        "        \"customerId\": \"c001\",\n" +
                        "        \"customerName\": \"customer2\",\n" +
                        "        \"contactName\": \"contact1\",\n" +
                        "        \"address\": \"main street\",\n" +
                        "        \"city\": \"blr\",\n" +
                        "        \"postalCode\": \"560077\",\n" +
                        "        \"country\": \"IN\"\n" +
                        "    }")
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .body(" {\n" +
                        "        \"customerId\": \"c001\",\n" +
                        "        \"customerName\": \"customer2\",\n" +
                        "        \"contactName\": \"contact1\",\n" +
                        "        \"address\": \"main street\",\n" +
                        "        \"city\": \"blr\",\n" +
                        "        \"postalCode\": \"560077\",\n" +
                        "        \"country\": \"IN\"\n" +
                        "    }")
                .toPact();

        return requestResponsePact;
    }

    @PactVerification(fragment = "pactForPostCustomer")
    @Test
    public void testFor_POST() {
        log.info("CustomerServicePostPositiveTest::testFor_POST");
        final CustomerModel customerResponse = customerServiceClient.addCustomer();
        log.info("Customer Response: ");
        log.info(String.valueOf(customerResponse));
    }

}
