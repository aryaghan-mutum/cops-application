package com.copsapitest.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;

import au.com.dius.pact.model.RequestResponsePact;
import com.copsapitest.constants.CopsConstants;
import com.copsapitest.dto.CustomerDto;
import com.copsapitest.enumeration.CopsEnum;
import com.copsapitest.rest.CopsRestAssuredService;
import com.copsapitest.util.GeneralUtil;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerConsumerPactTest {

    private static String responseBody = "[\n" +
            "    {\n" +
            "        \"customerId\": \"A65M4V8Q15\",\n" +
            "        \"customerName\": \"Tamesha Cremin\",\n" +
            "        \"contactName\": \"Deja\",\n" +
            "        \"address\": \"Apt. 057 55934 Abshire Squares, Schadenland, WA 27377\",\n" +
            "        \"city\": \"Rosenbaumshire\",\n" +
            "        \"postalCode\": \"23174\",\n" +
            "        \"country\": \"Cyprus\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"Z11B595DF5\",\n" +
            "        \"customerName\": \"Sheila Kohler\",\n" +
            "        \"contactName\": \"Jettie\",\n" +
            "        \"address\": \"7622 Mason Viaduct, Linofort, TX 97713\",\n" +
            "        \"city\": \"East Sandeemouth\",\n" +
            "        \"postalCode\": \"86858-1069\",\n" +
            "        \"country\": \"Austria\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"S3GEL4876R\",\n" +
            "        \"customerName\": \"Margarito Abernathy\",\n" +
            "        \"contactName\": \"Claudio\",\n" +
            "        \"address\": \"Apt. 485 71083 Brigitte Centers, Dietrichshire, DE 46371\",\n" +
            "        \"city\": \"Port Gabrielview\",\n" +
            "        \"postalCode\": \"79883-1932\",\n" +
            "        \"country\": \"Jordan\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"39BJ2AN3E8\",\n" +
            "        \"customerName\": \"Dusty King MD\",\n" +
            "        \"contactName\": \"Chung\",\n" +
            "        \"address\": \"Suite 986 0122 Tuan Plains, Gilberteburgh, NH 78226-6328\",\n" +
            "        \"city\": \"Marquardtberg\",\n" +
            "        \"postalCode\": \"92115\",\n" +
            "        \"country\": \"Portugal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"M17472IP76\",\n" +
            "        \"customerName\": \"Gilberto Marquardt\",\n" +
            "        \"contactName\": \"Boris\",\n" +
            "        \"address\": \"447 Schmidt Plaza, Port Tylermouth, CA 10899\",\n" +
            "        \"city\": \"Kenethfurt\",\n" +
            "        \"postalCode\": \"44890-9873\",\n" +
            "        \"country\": \"Timor-Leste\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"712UXF8ZKY\",\n" +
            "        \"customerName\": \"Maye Collins\",\n" +
            "        \"contactName\": \"Cheryle\",\n" +
            "        \"address\": \"Apt. 899 052 Brekke Drives, North Willie, LA 09500-4418\",\n" +
            "        \"city\": \"North Meghanchester\",\n" +
            "        \"postalCode\": \"98581-4105\",\n" +
            "        \"country\": \"Congo, Democratic Republic of the\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"8ABZ16C3R6\",\n" +
            "        \"customerName\": \"Rana Von\",\n" +
            "        \"contactName\": \"Maryjane\",\n" +
            "        \"address\": \"4137 Nathanael Path, East Karla, OH 58291-6177\",\n" +
            "        \"city\": \"New Isistown\",\n" +
            "        \"postalCode\": \"29372\",\n" +
            "        \"country\": \"Chile\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"75J874WW99\",\n" +
            "        \"customerName\": \"Ms. Mason Prosacco\",\n" +
            "        \"contactName\": \"Kennith\",\n" +
            "        \"address\": \"320 Mertz Neck, East Babetteborough, SD 28236-7747\",\n" +
            "        \"city\": \"Erickfort\",\n" +
            "        \"postalCode\": \"16806-2450\",\n" +
            "        \"country\": \"Latvia\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"7P483826AN\",\n" +
            "        \"customerName\": \"Max Flatley\",\n" +
            "        \"contactName\": \"Lala\",\n" +
            "        \"address\": \"Suite 126 0160 Marcel Cliffs, West Pearlenehaven, CA 38177\",\n" +
            "        \"city\": \"North Mohammed\",\n" +
            "        \"postalCode\": \"80050\",\n" +
            "        \"country\": \"Trinidad and Tobago\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"customerId\": \"6Z9O9E22Q3\",\n" +
            "        \"customerName\": \"Christal Runolfsson PhD\",\n" +
            "        \"contactName\": \"Levi\",\n" +
            "        \"address\": \"01073 Bahringer Trail, Lake Giuseppeton, TX 84345\",\n" +
            "        \"city\": \"East Burl\",\n" +
            "        \"postalCode\": \"41914-3387\",\n" +
            "        \"country\": \"Djibouti\"\n" +
            "    }\n" +
            "]";


    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", "localhost", GeneralUtil.getAvailablePort(), this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        RequestResponsePact requestResponsePact = builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/pact")
                .method(HttpMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(headers)
                .body(responseBody)
                .toPact();

        return requestResponsePact;
    }

    @Test
    @PactVerification()
    public void getAllCustomers() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId("123");
        customerDto.setCustomerName("anurag");
        customerDto.setContactName("anu");
        customerDto.setAddress("123 5th st");
        customerDto.setCity("miami");
        customerDto.setPostalCode("32456");
        customerDto.setCountry("usa");

        List<CustomerDto> expectedCustomerDtoList = Arrays.asList(customerDto, customerDto);

        // when
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/pact", String.class);

     //   RestTemplate restTemplate = new RestTemplateBuilder().rootUri(mockProvider.getUrl()).build();

        Response getCustomerResponse = new CopsRestAssuredService().callRestAssuredGetService(CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchGETServiceUrl());
        List<String> actualCustomerDtoList = getCustomerResponse.jsonPath().get();
        Assertions.assertEquals(expectedCustomerDtoList, actualCustomerDtoList);
    }
}
