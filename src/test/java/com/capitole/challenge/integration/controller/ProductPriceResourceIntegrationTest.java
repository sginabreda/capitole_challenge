package com.capitole.challenge.integration.controller;

import com.capitole.challenge.application.controller.ProductPriceResource;
import com.capitole.challenge.domain.entity.ProductPrice;
import com.capitole.challenge.domain.exception.ProductPriceNotFoundException;
import com.capitole.challenge.domain.usecase.FindProductPriceUseCase;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductPriceResource.class)
@AutoConfigureWebClient
@ActiveProfiles("test")
public class ProductPriceResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FindProductPriceUseCase useCase;

    private final String date = "2020-06-14-11:00:00";
    private final String productId = "1";
    private final String brandId = "1";

    @Test
    void testFindProductPrice_shouldReturnProductPrice() throws Exception {
        // Given
        ProductPrice productPrice = new ProductPrice(
                1L,
                LocalDateTime.parse("2020-06-14T10:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                1,
                35455L,
                1,
                BigDecimal.valueOf(34.55),
                "EUR");
        String expectedProductPriceJson = """
                {
                  "product_id": 35455,
                  "brand_id": 1,
                  "price_list": 1,
                  "start_date": "2020-06-14T10:00:00",
                  "end_date": "2020-12-31T23:59:59",
                  "final_price": 34.55
                }
                """;

        // When / Then
        when(useCase.findProductPrice(any(), any(), any())).thenReturn(productPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices?date=" + date + "&productId=" + productId + "&brandId=" + brandId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedProductPriceJson));
    }

    @Test
    void testFindProductPrice_whenPriceNotFound_shouldReturnApiError() throws Exception {
        // Given
        String expectedApiErrorJson = """
                {
                  "code": "not.found",
                  "message": "Product price not found"
                }
                """;

        // When / Then
        when(useCase.findProductPrice(any(), any(), any())).thenThrow(new ProductPriceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/prices?date=" + date + "&productId=" + productId + "&brandId=" + brandId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json(expectedApiErrorJson));
    }
}
