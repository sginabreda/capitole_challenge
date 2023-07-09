package com.capitole.challenge.unit.infrastructure.mapper;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.infrastructure.mapper.ProductPriceModelMapper;
import com.capitole.challenge.infrastructure.model.ProductPriceModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProducePriceModelMapperTest {

    @Test
    void testToDomain_shouldMapModelEntityToDomainClass() {
        // Given
        ProductPriceModel priceModel = new ProductPriceModel(
                1L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                1,
                35455L,
                0,
                BigDecimal.valueOf(10L),
                "EUR"
        );

        // When
        ProductPrice productPrice = ProductPriceModelMapper.toDomain(priceModel);

        // Then
        assertEquals(priceModel.getProductId(), productPrice.productId());
        assertEquals(priceModel.getBrandId(), productPrice.brandId());
        assertEquals(priceModel.getStartDate(), productPrice.startDate());
        assertEquals(priceModel.getEndDate(), productPrice.endDate());
        assertEquals(priceModel.getPriceList(), productPrice.priceList());
        assertEquals(priceModel.getPrice(), productPrice.price());
        assertEquals(priceModel.getPriority(), productPrice.priority());
        assertEquals(priceModel.getCurrency(), productPrice.currency());
    }
}
