package com.edumunera.comercio.application.ports.output;

import com.edumunera.comercio.domain.model.Prices;

import java.util.List;
import java.util.Optional;

public interface PricesPersistencePort {

    List<Prices> getAllPrices();

    Optional<Prices> getPrice(Prices prices);

}
