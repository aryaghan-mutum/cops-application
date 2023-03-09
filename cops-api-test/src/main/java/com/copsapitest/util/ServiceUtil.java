package com.copsapitest.util;

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

    @Description("flatten a json string")
    public static Map<String, Object> flattenJson(String response) {
        return JsonFlattener.flattenAsMap(response);
    }

    @SneakyThrows
    public static String convertPojoToJson(Object objectDto) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(objectDto);
    }

    public static String generateAuthToken() {
        return UUID.randomUUID().toString();
    }
}
