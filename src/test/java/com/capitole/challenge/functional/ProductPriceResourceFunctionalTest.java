package com.capitole.challenge.functional;

import com.capitole.challenge.application.dto.response.ApiErrorDto;
import com.capitole.challenge.application.dto.response.ProductPriceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ProductPriceResourceFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    private String pricesControllerUri;

    @BeforeEach
    void setUp() {
        pricesControllerUri = restTemplate.getRootUri() + "/prices";
    }

    @ParameterizedTest(name = "Case #{index} - should return the {3} record in PRICES")
    @CsvSource(value = {
            "2020-06-14T10:00:00,35455,1,first",
            "2020-06-14T16:00:00,35455,1,second",
            "2020-06-14T21:00:00,35455,1,first",
            "2020-06-15T10:00:00,35455,1,third",
            "2020-06-16T21:00:00,35455,1,fourth",
    })
    void testPricesEndpoint_givenCases_shouldReturnExpectedJsonResponse(
            String date,
            String productId,
            String brandId,
            String expectedPricesRecord
    ) throws IOException {
        // Given
        String finalUri = pricesControllerUri + "?date=" + date + "&productId=" + productId + "&brandId=" + brandId;
        String jsonResponse = new String(
                Files.readAllBytes(
                        ResourceUtils.getFile("classpath:prices-records/" + expectedPricesRecord + ".json")
                                .toPath())
        );
        ProductPriceDto expectedDto = objectMapper.readValue(jsonResponse, ProductPriceDto.class);

        // When
        ResponseEntity<ProductPriceDto> response = restTemplate.getForEntity(finalUri, ProductPriceDto.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "date=2020-06-14T11:00:00&productId=35455,Brand id parameter is required",
            "date=2020-06-14T11:00:00&productId=35455&brandId=,Invalid Brand id",
            "date=2020-06-14T11:00:00&brandId=1,Product id parameter is required",
            "date=2020-06-14T11:00:00&brandId=1&productId=,Invalid Product id",
            "brandId=1&productId=35455,Date parameter is required",
    })
    void testFindProductPrice_whenRequiredParameterIsNotSent_shouldReturnApiError(
            String queryParameters,
            String expectedErrorMessage
    ) {
        // Given
        String finalUri = pricesControllerUri + "?" + queryParameters;

        // When
        ResponseEntity<ApiErrorDto> response = restTemplate.getForEntity(finalUri, ApiErrorDto.class);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrorMessage, response.getBody().message());
    }
}
