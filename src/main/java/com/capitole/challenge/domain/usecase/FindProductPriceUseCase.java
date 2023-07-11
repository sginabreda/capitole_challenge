package com.capitole.challenge.domain.usecase;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.infrastructure.db.ProductPriceRepository;
import com.capitole.challenge.infrastructure.db.mapper.ProductPriceModelMapper;
import com.capitole.challenge.infrastructure.db.model.ProductPriceModel;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindProductPriceUseCase {

    private final ProductPriceRepository repository;

    public FindProductPriceUseCase(ProductPriceRepository repository) {
        this.repository = repository;
    }

    public ProductPrice findProductPrice(LocalDateTime date, Long productId, Long brandId) {
        List<ProductPriceModel> prices = repository.findProductPrices(date, productId, brandId);

        ProductPriceModel finalProductPrice;

        if (prices.isEmpty()) {
          throw new ProductPriceNotFoundException();
        }

        finalProductPrice = Collections.max(prices, Comparator.comparingInt(ProductPriceModel::getPriority));
        return ProductPriceModelMapper.toDomain(finalProductPrice);
    }
}
