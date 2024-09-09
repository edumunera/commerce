package com.edumunera.comercio.infraestructure.adapters.output.persistence.mapper;

import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.infraestructure.adapters.output.persistence.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PricesMapper {

    PricesEntity pricesDtoToEntity(Prices prices);

    Prices pricesEntityToDto(PricesEntity pricesEntity);

    List<Prices> pricesEntityToDto(List<PricesEntity> pricesEntity);

    List<PricesEntity> pricesDtoToEntity(List<Prices> pricesEntity);

}

