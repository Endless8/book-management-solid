package org.example.bookmanagement.wrapper;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;

public interface MapperWrapper {

    <T> T convertValue(Object fromValue, TypeReference<T> typeReference);

    void writeValue(File resultFile, Object value);

    <T> T readValue(File src, TypeReference<T> valueTypeRef);

    String writeValueAsString(Object value);
}
