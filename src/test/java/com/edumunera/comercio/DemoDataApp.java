package com.edumunera.comercio;

import com.edumunera.comercio.domain.model.Brand;
import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;

import java.time.LocalDateTime;

public class DemoDataApp {

    public static final long PRODUCT_ID = 35455L;
    public static final String EUR = "EUR";

    private DemoDataApp() {
    }

    public static Prices getPrice(int index) {
        return switch (index) {
            case 1 -> PRICES_1;
            case 2 -> PRICES_2;
            case 3 -> PRICES_3;
            case 4 -> PRICES_4;
            default -> throw new IllegalArgumentException("Invalid index: " + index);
        };
    }

    private static Prices createPrice(LocalDateTime startDate, LocalDateTime endDate, int priceList, double price, int priority) {
        return Prices.builder()
                .brand(Brand.builder().id(1).name("ZARA").build())
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(PRODUCT_ID)
                .priority(priority)
                .price(price)
                .curr(EUR)
                .build();
    }

    public static final Prices PRICES_1 = createPrice(
            LocalDateTime.of(2020, 6, 14, 0, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
            1,
            35.50,
            0
    );

    public static final Prices PRICES_2 = createPrice(
            LocalDateTime.of(2020, 6, 14, 15, 0, 0),
            LocalDateTime.of(2020, 6, 14, 18, 30, 0),
            2,
            25.45,
            1
    );

    public static final Prices PRICES_3 = createPrice(
            LocalDateTime.of(2020, 6, 15, 0, 0, 0),
            LocalDateTime.of(2020, 6, 15, 11, 0, 0),
            3,
            30.50,
            1
    );

    public static final Prices PRICES_4 = createPrice(
            LocalDateTime.of(2020, 6, 15, 16, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
            4,
            38.95,
            1
    );

    public static PricesFindRequest pricesResponseDto(String date) {
        return PricesFindRequest.builder()
                .brandId(1)
                .startDate(date)
                .productId(35455L)
                .build();
    }

    public static PricesResponse pricesResponseDto() {
        return PricesResponse.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .productId(35455L)
                .priceList(1)
                .price(35.50)
                .curr("EUR")
                .build();
    }
}