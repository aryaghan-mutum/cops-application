package com.copsapitest.util;

import com.copsapitest.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Description;

import java.util.Map;
import java.util.UUID;

public class ServiceUtil {

    @Description("flatten a json string")
    public static Map<String, Object> flattenJson(final Map<String, Object> response) {
        return JsonFlattener.flattenAsMap(response.get("response").toString());
    }

    @SneakyThrows
    public static String convertPojoToJson(CustomerDto customerDto) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(customerDto);
    }

    public static String generateAuthToken() {
        return UUID.randomUUID().toString();
    }
}
