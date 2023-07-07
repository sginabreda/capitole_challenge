package com.capitole.challenge.domain.usecase;

import com.capitole.challenge.domain.model.ProductPrice;
import com.capitole.challenge.domain.model.ProductPriceRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GetProductPriceUseCase {

    private final ProductPriceRepository repository;

    public GetProductPriceUseCase(ProductPriceRepository repository) {
        this.repository = repository;
    }

    public ProductPrice getProductPrice(LocalDateTime date, Long productId, Long brandId) {
        List<ProductPrice> prices = repository.findProductPrices(date, productId, brandId);

        if (prices.size() == 1) {
            return prices.get(0);
        } else {
            return Collections.max(prices, Comparator.comparingInt(ProductPrice::priority));
        }
    }
}
