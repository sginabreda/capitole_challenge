package com.capitole.challenge.application.dto.mapper;

import com.capitole.challenge.application.dto.response.ProductPriceDto;
import com.capitole.challenge.domain.entity.ProductPrice;

public class ProductPriceMapper {

    public static ProductPriceDto toDto(ProductPrice productPrice) {
        return new ProductPriceDto(
                productPrice.productId(),
                productPrice.brandId(),
                productPrice.priceList(),
                productPrice.startDate(),
                productPrice.endDate(),
                productPrice.price(),
                productPrice.currency());
    }
}
