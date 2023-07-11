package com.capitole.challenge.application.controller;

import com.capitole.challenge.application.dto.request.FindProductPriceRequest;
import com.capitole.challenge.application.dto.response.ProductPriceDto;
import com.capitole.challenge.application.dto.mapper.ProductPriceMapper;
import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("prices")
public class ProductPriceResource {

    private final FindProductPriceUseCase findProductPriceUseCase;

    public ProductPriceResource(FindProductPriceUseCase findProductPriceUseCase) {
        this.findProductPriceUseCase = findProductPriceUseCase;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductPriceDto findProductPrice(@Valid FindProductPriceRequest request) {
        ProductPrice productPrice = findProductPriceUseCase.findProductPrice(
                request.getDate(),
                Long.parseLong(request.getProductId()),
                Long.parseLong(request.getBrandId()));
        return ProductPriceMapper.toDto(productPrice);
    }
}
