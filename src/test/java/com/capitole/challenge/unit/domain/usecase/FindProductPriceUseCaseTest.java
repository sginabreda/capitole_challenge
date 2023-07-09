package com.capitole.challenge.unit.domain.usecase;

import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import com.capitole.challenge.infrastructure.db.ProductPriceRepository;
import com.capitole.challenge.infrastructure.model.ProductPriceModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FindProductPriceUseCaseTest {

    private final ProductPriceRepository repository = Mockito.mock();
    private final FindProductPriceUseCase useCase = new FindProductPriceUseCase(repository);

    private final LocalDateTime date = LocalDateTime.now();
    private final Long productId = 35455L;
    private final Long brandId = 1L;

    @BeforeEach
    void setUp() {
        reset(repository);
    }

    @Test
    void testThat_whenOnlyOnePriceFound_shouldReturnPriceFound() {
        // Given
        ProductPriceModel priceModel = new ProductPriceModel(1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, 35455L, 0, new BigDecimal(34L), "EUR");

        // When
        when(repository.findProductPrices(any(), any(), any())).thenReturn(List.of(priceModel));

        ProductPrice productPrice = useCase.findProductPrice(date, productId, brandId);

        // Then
        assertEquals(productPrice.brandId(), priceModel.getBrandId());
        assertEquals(productPrice.productId(), priceModel.getProductId());
        assertEquals(productPrice.startDate(), priceModel.getStartDate());
        assertEquals(productPrice.endDate(), priceModel.getEndDate());
        assertEquals(productPrice.priceList(), priceModel.getPriceList());
        assertEquals(productPrice.price(), priceModel.getPrice());
        assertEquals(productPrice.priority(), priceModel.getPriority());
        assertEquals(productPrice.currency(), priceModel.getCurrency());
        verify(repository, times(1)).findProductPrices(date, productId, brandId);
    }

    @Test
    void testThat_whenMultiplePricesFound_shouldReturnPriceWithHighestPriority() {
        // When
        when(repository.findProductPrices(any(), any(), any())).thenReturn(List.of(
                new ProductPriceModel(1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, 35455L, 0, new BigDecimal(34L), "EUR"),
                new ProductPriceModel(2L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, 35455L, 1, new BigDecimal(39L), "EUR")
        ));

        ProductPrice productPrice = useCase.findProductPrice(date, productId, brandId);

        // Then
        assertEquals(1, productPrice.priority());
        assertEquals(new BigDecimal(39L), productPrice.price());
        assertEquals("EUR", productPrice.currency());
        assertEquals(1L, productPrice.brandId());
        verify(repository, times(1)).findProductPrices(date, productId, brandId);
    }

    @Test
    void testThat_whenNoPricesFound_shouldThrowANotFoundException() {
        // When
        when(repository.findProductPrices(any(), any(), any())).thenReturn(Collections.emptyList());

        // Then
        assertThrows(ProductPriceNotFoundException.class, () -> useCase.findProductPrice(date, productId, brandId));
    }
}
