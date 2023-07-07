package com.capitole.challenge.delivery.mapper;

import com.capitole.challenge.delivery.dto.ProductPriceDto;
import com.capitole.challenge.domain.model.ProductPrice;

public class ProductPriceMapper {

    public static ProductPriceDto toDto(ProductPrice productPrice) {
        return new ProductPriceDto(
                productPrice.productId(),
                productPrice.brandId(),
                productPrice.priceList(),
                productPrice.startDate(),
                productPrice.endDate(),
                productPrice.price());
    }
}
