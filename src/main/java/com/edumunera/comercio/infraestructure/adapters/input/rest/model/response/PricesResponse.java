package com.edumunera.comercio.infraestructure.adapters.input.rest.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Builder
public record PricesResponse(

        Long productId,

        Integer brandId,

        Integer priceList,

        Double price,

        String curr,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm:ss")
        LocalDateTime startDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm:ss")
        LocalDateTime endDate
) {
}

