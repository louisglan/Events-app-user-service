package com.louis.user_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
    public static <T> T readJson(String path, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resourceInputStream = new ClassPathResource(path).getInputStream();
        return objectMapper.readValue(resourceInputStream, clazz);
    }

    public static String getJsonString(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(readJson(path, JsonNode.class));
    }
}
