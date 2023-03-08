package com.copsapitest.validators;

import org.junit.jupiter.api.Assertions;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class CommonApiValidator {

    @Description("validate status code for the response")
    public static void validateStatusCode(String testDescription, Map<String, Object> responseMap) {
        if (Integer.parseInt(responseMap.get("statusCode").toString()) == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseMap.get("statusCode"), String.format(testDescription));
        } else {
            Assertions.assertEquals(String.valueOf(HttpStatus.OK.value()), responseMap.get("statusCode"), String.format(testDescription));
        }
    }
}
