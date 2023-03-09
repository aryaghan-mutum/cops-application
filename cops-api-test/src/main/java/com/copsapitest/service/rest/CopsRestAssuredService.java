//package com.copsapitest.service.rest;
//
//import org.springframework.context.annotation.Description;
//
////import io.restassured.response.Response;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//
//public class CopsRestAssuredService {
//
//    @Description("call a POST Http service using rest assured")
//    public Response getTowelBorrowRespStatusCode(String endpoint, Object requestBody) {
//        return given().relaxedHTTPSValidation()
//                .when()
//                .baseUri(endpoint)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .body(requestBody)
//                .post();
//
////        Response response = given()
////                .contentType(ContentType.JSON)
////                .when()
////                .get(CUSTOMER_GET_ENDPOINT)
////                .then()
////                .extract().response();
//    }
//}
