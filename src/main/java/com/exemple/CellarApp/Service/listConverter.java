package com.exemple.CellarApp.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class listConverter implements AttributeConverter<List<Object>, String> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final Logger LOGGER = LoggerFactory.getLogger(listConverter.class);

    @Override
    public String convertToDatabaseColumn(List<Object> bottleList) {
        String listJson = null;
        try {
            listJson = objectMapper.writeValueAsString(bottleList);
        } catch (final JsonProcessingException e) {
            LOGGER.error("JSON writing error", e);
        }
        return listJson;
    }

    @Override
    public List<Object> convertToEntityAttribute(String json) {
        List<Object> listObject = null;
        try {
            listObject = objectMapper.readValue(json, List.class);
        } catch (final IOException e) {
            LOGGER.error("JSON reading error", e);
        }
        return listObject;
    }
}
