package com.copsapitest.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.util.Map;

@Slf4j
public class LogUtil {

    @Description("log the response")
    public static void logResponse(Map<String, Object> responseMap) {
        log.info("----------------------------------------------------------------------");
    //    log.info("TEST_DESCRIPTION: " + responseMap.get("testDescription").toString());
//        log.info("ENDPOINT:" + responseMap.get("endpoint").toString());
  //      log.info("STATUS_CODE: " + responseMap.get("statusCode").toString());
   //     log.info("RESPONSE: " + responseMap.get("response").toString());
        log.info("----------------------------------------------------------------------");
    }
}
