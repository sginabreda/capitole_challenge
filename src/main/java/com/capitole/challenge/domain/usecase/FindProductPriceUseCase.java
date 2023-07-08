package com.capitole.challenge.domain.usecase;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.infrastructure.db.ProductPriceRepository;
import com.capitole.challenge.infrastructure.mapper.ProductPriceModelMapper;
import com.capitole.challenge.infrastructure.model.ProductPriceModel;
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

        if (!prices.isEmpty()) {
            if (prices.size() == 1) {
                finalProductPrice = prices.get(0);
            } else {
                finalProductPrice = Collections.max(prices, Comparator.comparingInt(ProductPriceModel::getPriority));
            }
        } else {
            throw new ProductPriceNotFoundException();
        }

        return ProductPriceModelMapper.toDomain(finalProductPrice);
    }
}
