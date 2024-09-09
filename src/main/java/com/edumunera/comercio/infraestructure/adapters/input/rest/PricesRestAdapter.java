package com.edumunera.comercio.infraestructure.adapters.input.rest;

import com.edumunera.comercio.application.ports.input.PricesServicePort;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;
import com.edumunera.comercio.infraestructure.utils.JsonUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@Slf4j
@RequiredArgsConstructor
public class PricesRestAdapter {

    private final PricesServicePort pricesService;

    private final JsonUtil jsonUtil;

    @GetMapping
    public ResponseEntity<List<PricesResponse>> getPrices() {
        List<PricesResponse> pricesList = pricesService.getAllPrices();
        log.info("Prices {}", jsonUtil.toJsonString(pricesList));

        return ResponseEntity.ok(pricesList);
    }

    @PostMapping(headers = "X-API-VERSION=1")
    public ResponseEntity<PricesResponse> getPriceByPriority(@Valid @RequestBody PricesFindRequest pricesFindRequest) {
        log.info(pricesFindRequest.formattedRequest());
        PricesResponse pricesResponse = pricesService.getPrice(pricesFindRequest);
        log.info("Price {}", jsonUtil.toJsonString(pricesResponse));

        return ResponseEntity.ok(pricesResponse);
    }
}