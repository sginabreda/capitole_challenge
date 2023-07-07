package com.capitole.challenge.application.controller;

import com.capitole.challenge.delivery.ProductPriceController;
import com.capitole.challenge.delivery.dto.ProductPriceDto;
import com.capitole.challenge.delivery.mapper.ProductPriceMapper;
import com.capitole.challenge.domain.usecase.GetProductPriceUseCase;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prices")
public class ProductPriceResource implements ProductPriceController {

    private final GetProductPriceUseCase getProductPriceUseCase;

    public ProductPriceResource(GetProductPriceUseCase getProductPriceUseCase) {
        this.getProductPriceUseCase = getProductPriceUseCase;
    }

    @Override
    public ProductPriceDto getProductPrice(LocalDateTime date, Long productId, Long brandId) {
        return ProductPriceMapper.toDto(getProductPriceUseCase.getProductPrice(date, productId, brandId));
    }
}
