package com.edumunera.comercio;

import com.edumunera.comercio.application.ports.input.PricesServicePort;
import com.edumunera.comercio.application.ports.output.PricesPersistencePort;
import com.edumunera.comercio.domain.exception.ErrorGeneralException;
import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static com.edumunera.comercio.application.ErrorApp.PRICE_NOT_FOUND;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class PricesRetrievalServiceTest {

    @MockBean
    private PricesPersistencePort pricesPersistenceAdapter;

    @Autowired
    private PricesServicePort pricesService;

    @Test
    void testGetPrice_whenPriceExists() {
        when(pricesPersistenceAdapter.getPrice(any(Prices.class))).thenReturn(Optional.of(DemoDataApp.getPrice(1)));
        PricesResponse result = pricesService.getPrice(DemoDataApp.pricesResponseDto("2020-06-14T00:00:00"));
        assertNotNull(result);
        assertEquals(DemoDataApp.pricesResponseDto(), result);
    }

    @Test
    void testGetPrice_whenPriceDoesNotExist() {
        when(pricesPersistenceAdapter.getPrice(any(Prices.class))).thenReturn(Optional.empty());

        ErrorGeneralException exception = assertThrows(ErrorGeneralException.class, () -> {
            pricesService.getPrice(any(PricesFindRequest.class));
        });

        assertEquals(PRICE_NOT_FOUND.getMsg(), exception.getMessage());
        assertEquals(PRICE_NOT_FOUND.getCode(), exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}
