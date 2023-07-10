package com.capitole.challenge.application.controller;

import com.capitole.challenge.delivery.ProductPriceController;
import com.capitole.challenge.delivery.dto.ProductPriceDto;
import com.capitole.challenge.delivery.mapper.ProductPriceMapper;
import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    public ProductPriceDto findProductPrice(@Valid FindProductPriceRequest request) {
        ProductPrice productPrice = findProductPriceUseCase.findProductPrice(
                request.getDate(),
                Long.parseLong(request.getProductId()),
                Long.parseLong(request.getBrandId()));
        return ProductPriceMapper.toDto(productPrice);
    }
}
