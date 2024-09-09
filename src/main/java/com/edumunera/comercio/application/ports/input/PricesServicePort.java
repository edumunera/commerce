package com.edumunera.comercio.application.ports.input;

import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;

import java.util.List;

public interface PricesServicePort {

    List<PricesResponse> getAllPrices();

    PricesResponse getPrice(PricesFindRequest pricesFindRequest);
}
