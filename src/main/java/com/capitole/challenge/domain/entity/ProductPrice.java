package com.capitole.challenge.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPrice(
        Long brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Long productId,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
