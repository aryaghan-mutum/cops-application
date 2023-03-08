package com.copsapitest.service.webclient;

import com.copsapitest.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CopsWebClientService {

    @Description("call a POST Http service using webclient")
    public Map<String, Object> callWebClientPostService(String testDescription, String endpoint, Map<String, Object> requestBody) {
        log.info("CopsWebClientService::callWebClientPostService");
        final Map<String, Object> responseMap = new HashMap<>();

        String responseSpec = WebClient
                .builder().build().post()
                .uri(endpoint)
                .headers(h -> h.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE)))
                .body(BodyInserters.fromValue(requestBody))
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        clientResponse.body((clientHttpResponse, context) -> {
                            return clientHttpResponse.getBody();
                        });
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        clientResponse.body((clientHttpResponse, context) -> {
                            responseMap.put("testDescription", testDescription);
                            responseMap.put("endpoint", endpoint);
                            responseMap.put("statusCode", String.valueOf(clientHttpResponse.getStatusCode().value()));
                            responseMap.put("responseMsg", clientHttpResponse.getStatusCode().name());
                            responseMap.put("response", clientHttpResponse.getBody());

                            return clientHttpResponse.getBody();
                        });

                        return clientResponse.bodyToMono(String.class);
                    }
                })
                .block();

        LogUtil.logResponse(responseMap);
        return responseMap;
    }


    /**
     * WebClient webClient = WebClient.create();
     * String responseJson = webClient.get()
     *         .uri(endPoint)
     *         .exchange()
     *         .block()
     *         .bodyToMono(String.class)
     *         .block();
     *
     * WebClient webClient = WebClient.create();
     * String responseJson = webClient.get()
     *         .uri("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
     *         .retrieve()
     *         .bodyToMono(String.class)
     *         .block();
     */

}
