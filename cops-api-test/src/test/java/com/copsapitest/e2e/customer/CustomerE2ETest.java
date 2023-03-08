package com.copsapitest.e2e.customer;

import com.copsapitest.constants.CopsConstants;
import com.copsapitest.enumeration.CopsEnum;
import com.copsapitest.service.http.CopsHttpService;
import com.copsapitest.service.webclient.CopsWebClientService;
import com.copsapitest.requestdata.CustomerRequestData;
import com.copsapitest.util.ServiceUtil;
import com.copsapitest.validators.CommonApiValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.Map;

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
        CustomerRequestData customerUtil = new CustomerRequestData();
        customerRequestMap = customerUtil.addCustomer();
        //customerRequestBody = ServiceUtil.convertPojoToJson(customerRequestMap);
    }

    @AfterEach
    public void tearDown() {
        log.info("CustomerE2ETest::tearDown");
    }

    @Test
    @Description("test e2e customer api")
    public void testCustomerE2EService() {
        log.info("CustomerE2ETest::testCustomerE2EService");
        CopsWebClientService copsWebClientService = new CopsWebClientService();
        Map<String, Object> postResponseMap = copsWebClientService.callWebClientPostService("CUSTOMER_POST_API", CUSTOMER_POST_ENDPOINT, customerRequestMap);
        CommonApiValidator.validateStatusCode(TEST_DESCRIPTION, postResponseMap);

        CopsHttpService copsHttpService = new CopsHttpService();
        Map<String, Object> getResponseMap = copsHttpService.callHttpGetService("CUSTOMER_GET_API", CUSTOMER_GET_ENDPOINT);
        CommonApiValidator.validateStatusCode(TEST_DESCRIPTION, postResponseMap);
        Map<String, Object> getFlattenResponseMap = ServiceUtil.flattenJson(getResponseMap);

        System.out.println();
    }
}
