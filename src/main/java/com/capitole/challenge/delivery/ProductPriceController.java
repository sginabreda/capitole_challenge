package com.capitole.challenge.delivery;

import com.capitole.challenge.delivery.dto.request.FindProductPriceRequest;
import com.capitole.challenge.delivery.dto.response.ProductPriceDto;

public interface ProductPriceController {
    ProductPriceDto findProductPrice(FindProductPriceRequest request);
}
