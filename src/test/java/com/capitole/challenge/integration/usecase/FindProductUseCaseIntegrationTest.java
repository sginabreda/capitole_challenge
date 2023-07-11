package com.capitole.challenge.integration.usecase;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class FindProductUseCaseIntegrationTest {

    @Autowired
    private FindProductPriceUseCase useCase;

    @Test
    void testFindProductPrice_shouldReturnPriceFound() {
        // Given
        LocalDateTime date = LocalDateTime.parse("2020-06-14T11:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // When
        ProductPrice productPrice = useCase.findProductPrice(date, productId, brandId);

        // Then
        assertEquals(35455L, productPrice.productId());
        assertEquals(1L, productPrice.brandId());
        assertEquals(0, productPrice.priority());
        assertTrue(date.isAfter(productPrice.startDate()) && date.isBefore(productPrice.endDate()));
    }

    @Test
    void testFindProductPrice_whenNoRecordsFound_shouldThrowNotFoundException() {
        // Given
        LocalDateTime date = LocalDateTime.parse("2020-06-13T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // When / Then
        assertThrows(ProductPriceNotFoundException.class, () -> useCase.findProductPrice(date, productId, brandId));
    }
}
