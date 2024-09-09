package com.edumunera.comercio.application.mapper;

import com.edumunera.comercio.domain.model.Prices;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.request.PricesFindRequest;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.PricesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PricesRestMapper {

    @Mapping(source = "brandId", target = "brand.id" )
    Prices toPrices(PricesFindRequest pricesFindRequest);

    @Mapping(source = "brand.id", target = "brandId" )
    PricesResponse toPricesResponse(Prices prices);

    @Mapping(source = "brandId", target = "brand.id" )
    List<PricesResponse> toPricesResponse(List<Prices> prices);

}
