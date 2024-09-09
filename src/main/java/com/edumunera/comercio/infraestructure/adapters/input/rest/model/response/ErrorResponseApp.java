package com.edumunera.comercio.infraestructure.adapters.input.rest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseApp<T>(

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm:ss")
        LocalDateTime localDateTime,

        T data,

        String code,

        String msg) {

    public ErrorResponseApp {
        localDateTime = LocalDateTime.now();
    }
}