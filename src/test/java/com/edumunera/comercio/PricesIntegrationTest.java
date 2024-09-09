package com.edumunera.comercio;

import com.edumunera.comercio.infraestructure.adapters.output.persistence.mapper.PricesMapper;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.repository.PricesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.edumunera.comercio.application.ErrorApp.PRICE_NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PricesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesMapper pricesMapper;

    @Autowired
    private PricesRepository pricesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices1() throws Exception {
        createTest("2020-06-14T10:00:00", 1, 35.5);
    }

    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices2() throws Exception {
        createTest("2020-06-14T16:00:00", 2, 25.45);
    }

    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices3() throws Exception {
        createTest("2020-06-14T21:00:00", 1, 35.5);
    }

    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices4() throws Exception {
        createTest("2020-06-15T10:00:00", 3, 30.5);
    }

    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices5() throws Exception {
        createTest("2020-06-16T21:00:00", 4, 38.95);
    }

    @DisplayName("Test 6: petición a las 21:00 del día 20 del año 2022 del producto 35455 para la brand 1 (ZARA)")
    @Test
    void testApiPrices6() throws Exception {
        createTestError();
    }

    private ResultActions getPerform(String startDate) throws Exception {
        return mockMvc.perform(post("/api/prices")
                .header("X-API-VERSION", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(DemoDataApp.pricesResponseDto(startDate))));
    }

    private void createTest(String startDate, int priceList, double price) throws Exception {
        getPerform(startDate)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(priceList))
                .andExpect(jsonPath("$.price").value(price));
    }

    private void createTestError() throws Exception {
        getPerform("2022-06-20T21:00:00")
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.code").value(PRICE_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.msg").value(PRICE_NOT_FOUND.getMsg()));
    }
}