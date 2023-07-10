package com.capitole.challenge.infrastructure.db.mapper;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.infrastructure.model.ProductPriceModel;

public class ProductPriceModelMapper {

    public static ProductPrice toDomain(ProductPriceModel productPriceModel) {
        return new ProductPrice(
                productPriceModel.getBrandId(),
                productPriceModel.getStartDate(),
                productPriceModel.getEndDate(),
                productPriceModel.getPriceList(),
                productPriceModel.getProductId(),
                productPriceModel.getPriority(),
                productPriceModel.getPrice(),
                productPriceModel.getCurrency()
        );
    }
}
