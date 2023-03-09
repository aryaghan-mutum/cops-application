package com.copsapitest.e2e.customer;

import com.copsapitest.constants.CopsConstants;
import com.copsapitest.dto.CustomerDto;
import com.copsapitest.enumeration.CopsEnum;
import com.copsapitest.rest.CopsRestAssuredService;
import com.copsapitest.requestdata.CustomerRequestData;
import com.copsapitest.util.ServiceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class CustomerE2ETest {

    private static final String TEST_DESCRIPTION = "CUSTOMER_E2E_TEST";

    private static final String CUSTOMER_GET_ENDPOINT = CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchGETServiceUrl();
    private static final String CUSTOMER_POST_ENDPOINT = CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchPOSTServiceUrl();
    private static final String CUSTOMER_DELETE_ENDPOINT = CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchDELETEServiceUrl();
    private static final String CUSTOMER_PUT_ENDPOINT = CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchPUTServiceUrl();

    private String customerRequestBody;
    private Map<String, Object> customerRequestMap;

    @BeforeEach
    public void setup() {
        log.info("CustomerE2ETest::setup");
        customerRequestMap = new CustomerRequestData().addCustomer();
        customerRequestBody = ServiceUtil.convertPojoToJson(customerRequestMap);
    }

    @AfterEach
    public void tearDown() {
        log.info("CustomerE2ETest::tearDown");
    }

//    @Test
//    @Description("test e2e customer api")
//    public void testCustomerE2EService() {
//        log.info("CustomerE2ETest::testCustomerE2EService");
//        Map<String, Object> postResponseMap = new CopsWebClientService().callWebClientPostService("CUSTOMER_POST_API", CUSTOMER_POST_ENDPOINT, customerRequestMap);
//        CommonApiValidator.validateStatusCode(TEST_DESCRIPTION, postResponseMap);
//
//        Map<String, Object> getResponseMap = new CopsHttpService().callHttpGetService("CUSTOMER_GET_API", CUSTOMER_GET_ENDPOINT);
//        CommonApiValidator.validateStatusCode(TEST_DESCRIPTION, postResponseMap);
//        Map<String, Object> getFlattenResponseMap = ServiceUtil.flattenJson(getResponseMap);
//
//        System.out.println();
//    }

    @Test
    @Description("test e2e customer api")
    public void testCustomerE2EService() throws JsonProcessingException {
        Response postCustomerResponse = new CopsRestAssuredService().callRestAssuredPostService(CUSTOMER_POST_ENDPOINT, customerRequestBody);
        Assertions.assertEquals(HttpStatus.OK.value(), postCustomerResponse.getStatusCode(), String.format("Status code do not match for %s", CUSTOMER_POST_ENDPOINT));

        Response getCustomerResponse = new CopsRestAssuredService().callRestAssuredGetService(CUSTOMER_GET_ENDPOINT);
        Assertions.assertEquals(HttpStatus.OK.value(), getCustomerResponse.getStatusCode(), String.format("Status code do not match for %s", CUSTOMER_GET_ENDPOINT));
        //Map<String, Object> getCustomerFlattenResponseMap = ServiceUtil.flattenJson(getCustomerResponse.getBody().asString());

        List<String> customerList = getCustomerResponse.jsonPath().get();
        customerList.stream().forEach(customer -> {
            System.out.println();
        });

        CustomerDto customerDto = new ObjectMapper().readValue(getCustomerResponse.getBody().asString(), CustomerDto.class);


    }
}
