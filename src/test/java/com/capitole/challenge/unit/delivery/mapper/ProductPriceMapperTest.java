package com.capitole.challenge.unit.delivery.mapper;

import com.capitole.challenge.delivery.dto.response.ProductPriceDto;
import com.capitole.challenge.delivery.mapper.ProductPriceMapper;
import com.capitole.challenge.domain.entity.ProductPrice;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductPriceMapperTest {

    @Test
    void testToDto_shouldMapDomainEntityToDtoClass() {
        // Given
        ProductPrice productPrice = new ProductPrice(
                1L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1,
                35455L,
                1,
                BigDecimal.valueOf(34.55d),
                "EUR");

        // When
        ProductPriceDto dto = ProductPriceMapper.toDto(productPrice);

        // Then
        assertEquals(productPrice.brandId(), dto.brandId());
        assertEquals(productPrice.productId(), dto.productId());
        assertEquals(productPrice.startDate(), dto.startDate());
        assertEquals(productPrice.endDate(), dto.endDate());
        assertEquals(productPrice.price(), dto.finalPrice());
        assertEquals(productPrice.priceList(), dto.priceList());
    }
}
