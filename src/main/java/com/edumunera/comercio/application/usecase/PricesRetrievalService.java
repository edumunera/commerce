package com.edumunera.comercio.application.usecase;

import com.edumunera.comercio.application.ports.input.PricesServicePort;
import com.edumunera.comercio.domain.exception.ErrorGeneralException;
import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.application.ports.output.PricesPersistencePort;
import com.edumunera.comercio.application.mapper.PricesRestMapper;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static com.edumunera.comercio.application.ErrorApp.PRICE_NOT_FOUND;

@RequiredArgsConstructor
public class PricesRetrievalService implements PricesServicePort {

    private final PricesPersistencePort pricesPersistencePort;

    private final PricesRestMapper pricesRestMapper;

    @Override
    public List<PricesResponse> getAllPrices() {
        return pricesRestMapper.toPricesResponse(pricesPersistencePort.getAllPrices());
    }

    @Override
    public PricesResponse getPrice(PricesFindRequest pricesFindRequest) {
        Optional<Prices> pricesResponse = pricesPersistencePort.getPrice(pricesRestMapper.toPrices(pricesFindRequest));

        return pricesRestMapper.toPricesResponse(pricesResponse.orElseThrow(() -> new ErrorGeneralException(PRICE_NOT_FOUND.getMsg(), PRICE_NOT_FOUND.getCode(), HttpStatus.NOT_FOUND)));
    }
}