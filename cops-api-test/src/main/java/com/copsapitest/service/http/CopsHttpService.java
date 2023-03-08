package com.copsapitest.service.http;

import com.copsapitest.util.LogUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class CopsHttpService {

    @SneakyThrows
    @Description("call a GET Http service using HttpURLConnection")
    public Map<String, Object> callHttpGetService(String testDescription, String endpoint) {
        final Map<String, Object> responseMap = new HashMap<>();
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HttpMethod.GET.name());
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        connection.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        responseMap.put("testDescription", testDescription);
        responseMap.put("endpoint", endpoint);
        responseMap.put("statusCode", connection.getResponseCode());
        responseMap.put("responseMsg", connection.getResponseMessage());

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            responseMap.put("response", response);
        } else {
            log.info("GET request not worked");
        }
        connection.disconnect();
        LogUtil.logResponse(responseMap);

        return responseMap;
    }

    @SneakyThrows
    @Description("call a POST Http service using HttpURLConnection")
    public Map<String, Object> callHttpPostService(String testDescription, String endpoint, String requestBody) {
        final Map<String, Object> responseMap = new HashMap<>();

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HttpMethod.POST.name());
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        connection.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        responseMap.put("testDescription", testDescription);
        responseMap.put("endpoint", endpoint);
        responseMap.put("statusCode", connection.getResponseCode());
        responseMap.put("responseMsg", connection.getResponseMessage());

        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(requestBody);
        writer.flush();
        writer.close();
        connection.getOutputStream().close();

        InputStream responseStream = connection.getResponseCode() / 100 == 2
                ? connection.getInputStream()
                : connection.getErrorStream();

        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";

        responseMap.put("response", response);
        LogUtil.logResponse(responseMap);

        return responseMap;
    }
}

