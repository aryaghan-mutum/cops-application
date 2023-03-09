package com.copsapitest.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

@Slf4j
public class CopsRestAssuredService {

    @Description("call a GET Http service using rest assured")
    public Response callRestAssuredGetService(String endpoint) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract().response();

        log.info("GET RESPONSE: ");
        log.info(response.getBody().asString());
        return response;
    }

    @Description("call a POST Http service using rest assured")
    public Response callRestAssuredPostService(String endpoint, String requestBody) {
        Response response = given()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .and()
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract().response();

        log.info("POST REQUEST: ");
        log.info(requestBody);
        log.info("----------------------------------------------------------");
        log.info("POST RESPONSE: ");
        log.info(response.getBody().asString());

        return response;
    }
}
