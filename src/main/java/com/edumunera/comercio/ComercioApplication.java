package com.edumunera.comercio;

import com.edumunera.comercio.application.ports.input.PricesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ComercioApplication {

    private final PricesServicePort pricesService;

    public static void main(String[] args) {
        SpringApplication.run(ComercioApplication.class, args);
    }
}
