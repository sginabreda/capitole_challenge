package com.capitole.challenge.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceDto(
        @JsonProperty("product_id")
        Long productId,
        @JsonProperty("brand_id")
        Long brandId,
        @JsonProperty("price_list")
        Integer priceList,
        @JsonProperty("start_date")
        LocalDateTime startDate,
        @JsonProperty("end_date")
        LocalDateTime endDate,
        @JsonProperty("final_price")
        BigDecimal finalPrice
) {
}
