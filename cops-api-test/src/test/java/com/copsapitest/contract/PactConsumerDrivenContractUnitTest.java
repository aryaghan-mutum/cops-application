package com.copsapitest.contract;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import com.copsapitest.util.GeneralUtil;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@PactTestFor(providerName = "customer_provider", hostInterface="localhost")
public class PactConsumerDrivenContractUnitTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("customer_provider", "localhost", GeneralUtil.getAvailablePort(), this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPactGetCustomers(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestResponsePact requestResponsePact = builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/pact")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("[\n" +
                        "    {\n" +
                        "        \"customerId\": \"A65M4V8Q15\",\n" +
                        "        \"customerName\": \"Tamesha Cremin\",\n" +
                        "        \"contactName\": \"Deja\",\n" +
                        "        \"address\": \"Apt. 057 55934 Abshire Squares, Schadenland, WA 27377\",\n" +
                        "        \"city\": \"Rosenbaumshire\",\n" +
                        "        \"postalCode\": \"23174\",\n" +
                        "        \"country\": \"Cyprus\"\n" +
                        "    }\n" +
                        "]").toPact();

        return requestResponsePact;
    }

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPactPostCustomers(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestResponsePact requestResponsePact = builder
                .given("test POST")
                .uponReceiving("POST REQUEST")
                .method("POST")
                .headers(headers)
                .body("{\n" +
                        "    \"customerId\": \"123\",\n" +
                        "    \"customerName\": \"Antonio Moreno Taquería\", \n" +
                        "    \"contactName\": \"Antonio Moreno\",\n" +
                        "    \"address\": \"Mataderos 2312\",\n" +
                        "    \"city\": \"México D.F.\",\n" +
                        "    \"postalCode\": \"05023\",\n" +
                        "    \"country\": \"Mexico\"\n" +
                        "}\n" +
                        "\n")
                .path("/pact")
                .willRespondWith()
                .status(200)
                .toPact();

        return requestResponsePact;
    }

    @Test
    @PactTestFor
    public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
        // when
        ResponseEntity<String> response = new RestTemplate()
                .getForEntity(mockProvider.getUrl() + "/pact", String.class);

        // then
        Assertions.assertThat(response.getStatusCode().value()).isEqualTo(200);
        Assertions.assertThat(response.getHeaders().get("Content-Type").contains(MediaType.APPLICATION_JSON_VALUE)).isTrue();
        Assertions.assertThat(response.getBody()).contains("customerId", "123", "customerName", "Antonio Moreno Taquería", "contactName", "Antonio Moreno", "address", "Mataderos 2312", "city", "México D.F.", "postalCode", "05023", "country", "Mexico");
    }

    @Test
    @PactTestFor
    public void givenPost_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = "{\n" +
                "    \"customerId\": \"987\",\n" +
                "    \"customerName\": \"Antonio Moreno Taquería\", \n" +
                "    \"contactName\": \"Antonio Moreno\",\n" +
                "    \"address\": \"Mataderos 2312\",\n" +
                "    \"city\": \"México D.F.\",\n" +
                "    \"postalCode\": \"05023\",\n" +
                "    \"country\": \"Mexico\"\n" +
                "}\n" +
                "\n";

        // when
        ResponseEntity<String> postResponse = new RestTemplate()
                .exchange(
                        mockProvider.getUrl() + "/create",
                        HttpMethod.POST,
                        new HttpEntity<>(jsonBody, httpHeaders),
                        String.class
                );

        //then
        Assertions.assertThat(postResponse.getStatusCode().value()).isEqualTo(200);
    }

}
