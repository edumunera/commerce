package com.edumunera.comercio.infraestructure.utils;

import com.edumunera.comercio.domain.exception.ErrorGeneralException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.edumunera.comercio.application.ErrorApp.JSON_PROCESSING_ERROR;

@Component
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper mapper;

    public <T> String toJsonString(T json){
        try {
            return mapper.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new ErrorGeneralException(JSON_PROCESSING_ERROR.getMsg(), JSON_PROCESSING_ERROR.getCode(), HttpStatus.BAD_REQUEST);
        }
    }
}
