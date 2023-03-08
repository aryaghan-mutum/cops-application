package com.copsapitest.smoke;

import com.copsapitest.constants.CopsConstants;
import com.copsapitest.enumeration.CopsEnum;
import com.copsapitest.service.http.CopsHttpService;
import com.copsapitest.util.ServiceUtil;
import com.copsapitest.validators.CommonApiValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.annotation.Description;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CustomerSmokeTest {

    private static final String CUSTOMER_ENDPOINT = CopsConstants.BASE_URL + CopsEnum.CUSTOMER.fetchGETServiceUrl();

    @Description("test data for customer service")
    public static Stream<Arguments> customerArguments() {
        return Stream.of(
                arguments("CUSTOMER_VALID"),
                arguments("CUSTOMER_INVALID"));
    }

    @ParameterizedTest(name = "#{index} : {0}")
    @MethodSource("customerArguments")
    public void testCustomerService(String desc) {
        CopsHttpService copsHttpService = new CopsHttpService();
        Map<String, Object> responseMap = copsHttpService.callHttpGetService(desc, CUSTOMER_ENDPOINT);
        Map<String, Object> flattenResponseMap = ServiceUtil.flattenJson(responseMap);
        CommonApiValidator.validateStatusCode(desc, responseMap);
        System.out.println();
    }

}
