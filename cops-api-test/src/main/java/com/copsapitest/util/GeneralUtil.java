package com.copsapitest.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@Slf4j
public class GeneralUtil {

    @Description("read json file based on path location")
    public static String readJsonFileAsString(String jsonFile) {
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(jsonFile)));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        
        return str;
    }

    public static int getAvailablePort() {
        return new Random()
                .ints(6000, 9000)
                .filter(GeneralUtil::isFree)
                .findFirst()
                .orElse(8080);
    }

    public static boolean isFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
