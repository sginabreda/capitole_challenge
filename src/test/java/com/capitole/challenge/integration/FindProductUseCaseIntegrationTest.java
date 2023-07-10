package com.capitole.challenge.integration;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import com.capitole.challenge.infrastructure.db.ProductPriceRepository;
import com.capitole.challenge.infrastructure.model.ProductPriceModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
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
    @Autowired
    private ProductPriceRepository repository;

    private final ProductPriceModel priceModel = new ProductPriceModel(
            1L,
            1L,
            LocalDateTime.parse("2020-06-14T10:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            1,
            35455L,
            0,
            BigDecimal.valueOf(10L),
            "EUR"
    );

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testFindProductPrice_shouldReturnPriceFound() {
        // Given
        repository.save(priceModel);
        LocalDateTime date = LocalDateTime.parse("2020-06-14T11:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // When
        ProductPrice productPrice = useCase.findProductPrice(date, productId, brandId);

        // Then
        assertEquals(productPrice.productId(), priceModel.getProductId());
        assertEquals(productPrice.brandId(), priceModel.getBrandId());
        assertEquals(productPrice.priority(), priceModel.getPriority());
        assertTrue(date.isAfter(productPrice.startDate()) && date.isBefore(productPrice.endDate()));
    }

    @Test
    void testFindProductPrice_whenNoRecordsFound_shouldThrowNotFoundException() {
        // Given
        repository.save(priceModel);
        LocalDateTime date = LocalDateTime.parse("2020-06-13T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        // When / Then
        assertThrows(ProductPriceNotFoundException.class, () -> useCase.findProductPrice(date, productId, brandId));
    }
}
