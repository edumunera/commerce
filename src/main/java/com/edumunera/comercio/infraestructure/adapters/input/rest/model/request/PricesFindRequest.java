package com.edumunera.comercio.infraestructure.adapters.input.rest.model.request;

import com.edumunera.comercio.infraestructure.adapters.input.rest.validation.ValidDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Valid
public record PricesFindRequest(
        @NotNull
        Integer brandId,

        @ValidDate
        String startDate,

        @NotNull
        Long productId
) {

    public String formattedRequest() {
        return "Brand ID: " + brandId + ", Start Date: " + startDate + ", Product ID: " + productId;
    }
}
