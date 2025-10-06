package com.louis.user_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
    public static <T> T readJson(String path, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream resourceInputStream = new ClassPathResource(path).getInputStream();
            return objectMapper.readValue(resourceInputStream, clazz);
        } catch (IOException exception) {
            throw new RuntimeException("Could not parse json from path: " + path, exception);
        }
    }

    public static String getJsonString(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(readJson(path, JsonNode.class));
        } catch (IOException exception) {
            throw new RuntimeException("Failed to convert JSON to string from path: " + path, exception);
        }
    }
}
