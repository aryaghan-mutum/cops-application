package com.copsperf.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.copsperf.constants.PathConstants;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    public static Map<String, Object> getTestDataMap(String testData) {
        return (Map<String, Object>) FileUtil.getFileContents(PathConstants.TEST_DATA_PATH).get(testData);
    }

    //"read json from resources based on path and class"
    @SneakyThrows
    public static <T> T readJsonFile(final String path, Class<?> target) {
        return (T) new ObjectMapper().readValue(new File(path), target);
    }

    @SneakyThrows
    public static String convertPojoToJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * get data from json file and store in a hash map
     */
    public static Map<String, Object> getFileContents(String file) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            File configFileObj = new File(file);
            map = mapper.readValue(configFileObj, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.getMessage();
        }
        return map;
    }
}
