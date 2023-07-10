package com.capitole.challenge.delivery;

import com.capitole.challenge.application.controller.FindProductPriceRequest;
import com.capitole.challenge.delivery.dto.ProductPriceDto;

public interface ProductPriceController {
    ProductPriceDto findProductPrice(FindProductPriceRequest request);
}
