package com.cops.util;

import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ServiceUtil {

    @Description("get api headers")
    public static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "1ee9b381-e71a-4e2f-8782-54ab1ce4d140");
        return headers;
    }
}
