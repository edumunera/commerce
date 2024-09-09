package com.edumunera.comercio.infraestructure.adapters.output.persistence;

import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.application.ports.output.PricesPersistencePort;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.entity.PricesEntity;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.repository.PricesRepository;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.mapper.PricesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PricesPersistenceAdapter implements PricesPersistencePort {

    private final PricesRepository pricesRepository;

    private final PricesMapper pricesMapper;

    @Override
    public List<Prices> getAllPrices() {
        return pricesMapper.pricesEntityToDto(pricesRepository.findAll());
    }

    @Override
    public Optional<Prices> getPrice(Prices prices) {
        PricesEntity pricesEntity = pricesMapper.pricesDtoToEntity(prices);
        Optional<PricesEntity> pricesDb = pricesRepository.findTopByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(pricesEntity.getBrand().getId(), pricesEntity.getStartDate(), pricesEntity.getStartDate(), pricesEntity.getProductId());
        return pricesDb.map(pricesMapper::pricesEntityToDto);
    }
}