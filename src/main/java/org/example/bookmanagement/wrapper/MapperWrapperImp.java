package org.example.bookmanagement.wrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MapperWrapperImp implements MapperWrapper {

    private final ObjectMapper objectMapper;

    public MapperWrapperImp() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T convertValue(Object fromValue, TypeReference<T> typeReference) {
        return objectMapper.convertValue(fromValue, typeReference);
    }

    @Override
    public void writeValue(File resultFile, Object value) {
        try {
            objectMapper.writeValue(resultFile, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T readValue(File src, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(src, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
