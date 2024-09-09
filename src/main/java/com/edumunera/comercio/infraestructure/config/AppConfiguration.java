package com.edumunera.comercio.infraestructure.config;

import com.edumunera.comercio.application.ports.input.PricesServicePort;
import com.edumunera.comercio.application.usecase.PricesRetrievalService;
import com.edumunera.comercio.application.ports.output.PricesPersistencePort;
import com.edumunera.comercio.application.mapper.PricesRestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    PricesServicePort pricesService(final PricesPersistencePort pricesRepository, final PricesRestMapper pricesRestMapper){
        return new PricesRetrievalService(pricesRepository, pricesRestMapper);
    }
}