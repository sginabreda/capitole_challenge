package com.capitole.challenge.delivery;

import com.capitole.challenge.delivery.dto.response.ProductPriceDto;
import java.time.LocalDateTime;

public interface ProductPriceController {
    ProductPriceDto findProductPrice(LocalDateTime date, Long productId, Long brandId);
}
