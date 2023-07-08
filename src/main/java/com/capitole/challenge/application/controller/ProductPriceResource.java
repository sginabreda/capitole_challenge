package com.capitole.challenge.application.controller;

import com.capitole.challenge.delivery.ProductPriceController;
import com.capitole.challenge.delivery.dto.response.ProductPriceDto;
import com.capitole.challenge.delivery.mapper.ProductPriceMapper;
import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prices")
public class ProductPriceResource implements ProductPriceController {

    private final FindProductPriceUseCase findProductPriceUseCase;

    public ProductPriceResource(FindProductPriceUseCase findProductPriceUseCase) {
        this.findProductPriceUseCase = findProductPriceUseCase;
    }

    @Override
    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ProductPriceDto findProductPrice(
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss") @RequestParam LocalDateTime date,
            @RequestParam(name = "product_id") Long productId,
            @RequestParam(name = "brand_id") Long brandId
    ) {
        ProductPrice productPrice = findProductPriceUseCase.findProductPrice(date, productId, brandId);
        return ProductPriceMapper.toDto(productPrice);
    }
}
