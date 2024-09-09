package com.edumunera.comercio;

import com.edumunera.comercio.application.ports.output.PricesPersistencePort;
import com.edumunera.comercio.domain.model.Brand;
import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.mapper.PricesMapper;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.repository.PricesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PricesPersistenceAdapterTest {

    private static final String PRICE_NO_DEBE_SER_NULO = "El resultado del método getPrice no debe ser nulo";
    private static final String PRICE_DEBE_COINCIDIR = "El precio debe coincidir";

    @MockBean
    private PricesRepository pricesRepository;

    @Autowired
    private PricesPersistencePort pricesPersistenceAdapter;

    @Autowired
    private PricesMapper pricesMapper;

    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test1() {
        verifyPriceAtTime(LocalDateTime.of(2020, 6, 14, 10, 0, 0), DemoDataApp.getPrice(1), 35.50);
    }

    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test2() {
        verifyPriceAtTime(LocalDateTime.of(2020, 6, 14, 16, 0, 0), DemoDataApp.getPrice(2), 25.45);
    }

    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test3() {
        verifyPriceAtTime(LocalDateTime.of(2020, 6, 14, 21, 0, 0), DemoDataApp.getPrice(1), 35.50);
    }

    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test4() {
        verifyPriceAtTime(LocalDateTime.of(2020, 6, 15, 10, 0, 0), DemoDataApp.getPrice(3), 30.50);
    }

    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void test5() {
        verifyPriceAtTime(LocalDateTime.of(2020, 6, 16, 21, 0, 0), DemoDataApp.getPrice(4), 38.95);
    }

    private void verifyPriceAtTime(LocalDateTime searchTime, Prices expectedPriceDemo, double expectedPriceValue) {
        Prices searchPrice = createSearchPrice(searchTime);
        setupMock(searchPrice, expectedPriceDemo);
        verifyPrice(searchPrice, expectedPriceValue);
    }

    private void setupMock(Prices searchPrice, Prices expectedPriceDemo) {
        when(pricesRepository.findTopByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                searchPrice.getBrand().getId(), searchPrice.getStartDate(), searchPrice.getStartDate(), searchPrice.getProductId()))
                .thenReturn(Optional.of(pricesMapper.pricesDtoToEntity(expectedPriceDemo)));
    }

    private void verifyPrice(Prices searchPrice, double expectedPriceValue) {
        Optional<Prices> result = pricesPersistenceAdapter.getPrice(searchPrice);
        assertNotNull(result, PRICE_NO_DEBE_SER_NULO);
        result.ifPresent(prices -> assertEquals(expectedPriceValue, prices.getPrice(), PRICE_DEBE_COINCIDIR));

        verify(pricesRepository, times(1)).findTopByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                searchPrice.getBrand().getId(), searchPrice.getStartDate(), searchPrice.getStartDate(), searchPrice.getProductId());

    }

    private static Prices createSearchPrice(LocalDateTime startDate) {
        return Prices.builder()
                .productId(35455L)
                .brand(Brand.builder().id(1).build())
                .startDate(startDate)
                .build();
    }
}
