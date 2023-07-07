package com.capitole.challenge.delivery;

import com.capitole.challenge.delivery.dto.ProductPriceDto;
import java.time.LocalDateTime;

public interface ProductPriceController {
    ProductPriceDto getProductPrice(LocalDateTime date, Long productId, Long brandId);
}
