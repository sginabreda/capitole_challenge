package com.capitole.challenge.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceDto(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal finalPrice
) {
}
