package com.edumunera.comercio.infraestructure.adapters.output.persistence.repository;

import com.edumunera.comercio.infraestructure.adapters.output.persistence.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

    Optional<PricesEntity> findTopByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
            @Param("brandId") Integer brandId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("productId") Long productId);
}